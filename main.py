from flask import Flask, render_template, request, jsonify, session
from langchain_groq import ChatGroq
from langchain_core.messages import SystemMessage, HumanMessage, AIMessage
import os
from dotenv import load_dotenv
import uuid

load_dotenv()

app = Flask(__name__)
app.secret_key = "career_secret_key_123"


llm = ChatGroq(
    api_key=os.getenv("GROQ_API_KEY"),
    model="llama-3.3-70b-versatile",
    temperature=0.6,
    max_tokens=250
)

SYSTEM_PROMPT = """
You are a professional career counselor inside a mobile app.

STRICT RULES:
- Keep answers SHORT (5–8 lines maximum)
- Use bullet points (•)
- No long paragraphs
- Give practical, clear advice
- Use user's profile details when available
- Continue context naturally
"""



memory_store = {}     # conversation memory
profile_store = {}    # personalization memory


def get_session_id():
    if "session_id" not in session:
        session["session_id"] = str(uuid.uuid4())
    return session["session_id"]




@app.route("/")
def home():
    session.clear()
    session["mode"] = "menu"
    return render_template("chat.html")


@app.route("/quiz")
def quiz():
    return "<h2>🎯 Career Quiz Page</h2><a href='/'>⬅ Back</a>"


@app.route("/content")
def content():
    return "<h2>📚 Career Content Page</h2><a href='/'>⬅ Back</a>"




@app.route("/chat", methods=["POST"])
def chat():
    user_input = request.json.get("message", "")
    lower_input = user_input.lower()
    mode = session.get("mode", "menu")
    session_id = get_session_id()

    # Initialize conversation memory
    if session_id not in memory_store:
        memory_store[session_id] = []

    # Initialize profile memory
    if session_id not in profile_store:
        profile_store[session_id] = {
            "stream": "",
            "interests": [],
            "skills": [],
            "goals": ""
        }

    profile = profile_store[session_id]


    if mode == "menu":

        if lower_input == "quiz":
            return jsonify({"redirect": "/quiz"})

        if lower_input == "content":
            return jsonify({"redirect": "/content"})

        if lower_input == "chat":
            session["mode"] = "chat"
            memory_store[session_id] = []
            return jsonify({
                "reply": "• Great! Tell me about your stream, interests or goals 😊"
            })

        return jsonify({
            "reply": "👋 What would you like to do today?",
            "buttons": [
                {"text": "🎯 Take Career Quiz", "value": "quiz"},
                {"text": "💬 Chat About Career", "value": "chat"},
                {"text": "📚 Explore Career Content", "value": "content"}
            ]
        })



    if mode == "chat":

        # -------- STREAM DETECTION --------
        if "science" in lower_input:
            profile["stream"] = "Science"
        elif "commerce" in lower_input:
            profile["stream"] = "Commerce"
        elif "arts" in lower_input:
            profile["stream"] = "Arts"

        # -------- INTEREST DETECTION --------
        interest_keywords = [
            "math", "biology", "coding", "design",
            "finance", "business", "ai", "computer",
            "medicine", "law", "psychology"
        ]

        for word in interest_keywords:
            if word in lower_input and word not in profile["interests"]:
                profile["interests"].append(word)

        # -------- SKILL DETECTION --------
        if "good at" in lower_input or "strong in" in lower_input:
            profile["skills"].append(user_input)

        # -------- GOAL DETECTION --------
        if "i want to" in lower_input:
            profile["goals"] = user_input

        # Save user message to conversation memory
        memory_store[session_id].append({
            "role": "user",
            "content": user_input
        })

        # Keep only last 10 messages
        conversation = memory_store[session_id][-10:]

        # Build profile context
        profile_context = f"""
User Profile:
Stream: {profile['stream']}
Interests: {profile['interests']}
Skills: {profile['skills']}
Goals: {profile['goals']}
"""

        # Build LLM messages
        messages = [SystemMessage(content=SYSTEM_PROMPT + profile_context)]

        for msg in conversation:
            if msg["role"] == "user":
                messages.append(HumanMessage(content=msg["content"]))
            else:
                messages.append(AIMessage(content=msg["content"]))

        # Get response
        response = llm.invoke(messages).content

        # Save assistant response
        memory_store[session_id].append({
            "role": "assistant",
            "content": response
        })

        return jsonify({"reply": response})

    return jsonify({"reply": "Session restarted."})



if __name__ == "__main__":
    app.run(debug=True)






