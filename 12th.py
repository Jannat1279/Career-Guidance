import streamlit as st
import pandas as pd
import pickle

# Load model and label encoder
with open("12th_future_pred.pkl", "rb") as f:
    model = pickle.load(f)

with open("label_encoder.pkl", "rb") as f:
    label_encoder = pickle.load(f)

# List of features (same order as in training)
features = ['Drawing', 'Dancing', 'Singing', 'Sports', 'Video Game', 'Acting', 'Travelling',
            'Gardening', 'Animals', 'Photography', 'teaching', 'exercise', 'coding',
            'electricity component', 'mechanic parts', 'computer parts', 'researching', 'architecture', 'historic collection',
            'botany', 'zoology', 'physics', 'accounting', 'economics',
            'sociology', 'geography', 'Psycology', 'history', 'science','business education',
            'chemistry', 'mathematics', 'biology', 'makeup', 'design','content writer','crafting','literature',
            'reading', 'cartooning', 'debating', 'astrology', 'hindi', 'french',
            'english', 'urdu', 'other language', 'solving puzzle', 'gymnastics',
            'yoga',
            'Engneering', 'Doctor', 'Pharmasist', 'Cycling', 'Knitting', 'Director',
            'Journalism', 'Business', 'Listening Music']

st.title("🎓 Career Recommendation System")

st.markdown("#### Select your interests and skills (0 or 1)")

user_input = []
for feature in features:
    value = st.slider(feature, 0, 1, 0)
    user_input.append(value)

if st.button("Recommend My Career"):
    prediction = model.predict([user_input])[0]
    course = label_encoder.inverse_transform([prediction])[0]
    st.success(f"✅ Recommended Course: **{course}**")