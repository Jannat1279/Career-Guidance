from flask import Flask, request, jsonify
import pickle
import numpy as np
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# Load model and label encoder
with open("12th_future_pred.pkl", "rb") as f:
    model = pickle.load(f)

with open("label_encoder.pkl", "rb") as f:
    label_encoder = pickle.load(f)

@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.get_json()
        answers = data.get("answers")
        if not answers or len(answers) != 59:
            return jsonify({"error": "Invalid input length"}), 400

        prediction = model.predict([answers])[0]
        career = label_encoder.inverse_transform([prediction])[0]
        return jsonify({"career": career})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)
