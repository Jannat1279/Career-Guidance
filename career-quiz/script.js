const features = [
  'Drawing','Dancing','Singing','Sports','Video Game','Acting','Travelling','Gardening','Animals','Photography','teaching','exercise','coding',
  'electricity component','mechanic parts','computer parts','researching','architecture','historic collection','botany','zoology','physics','accounting',
  'economics','sociology','geography','Psycology','history','science','business education','chemistry','mathematics','biology','makeup','design',
  'content writer','crafting','literature','reading','cartooning','debating','astrology','hindi','french','english','urdu','other language',
  'solving puzzle','gymnastics','yoga','Engneering','Doctor','Pharmasist','Cycling','Knitting','Director','Journalism','Business','Listening Music'
];

let username = "";
let email = "";
let selected = Array(features.length).fill(0);

const startBtn = document.getElementById("start-btn");
const usernameInput = document.getElementById("username");
const emailInput = document.getElementById("email");
const startContainer = document.getElementById("start-container");
const quizContainer = document.getElementById("quiz-container");
const questionsContainer = document.getElementById("questions-container");
const finishContainer = document.getElementById("finish-container");
const dashboardBtn = document.getElementById("dashboard-btn");
const welcomeText = document.getElementById("welcome-text");
const progress = document.getElementById("progress");

startBtn.addEventListener("click", () => {
  username = usernameInput.value.trim();
  email = emailInput.value.trim();
  if (!username || !email) {
    alert("Please enter both your name and email!");
    return;
  }
  startContainer.classList.add("hidden");
  quizContainer.classList.remove("hidden");
  welcomeText.innerHTML = `👋 Welcome <b>${username}</b>, select what you love!`;
  renderCards();
});

document.getElementById("darkToggle").addEventListener("change", () => {
  document.body.classList.toggle("dark-mode");
});

function renderCards() {
  features.forEach((text, index) => {
    const card = document.createElement("div");
    card.className = "question-box";
    card.innerHTML = `${index + 1}. ${text}`;
    card.onclick = () => toggleCard(card, index);
    questionsContainer.appendChild(card);
  });
}

function toggleCard(card, index) {
  selected[index] = selected[index] === 0 ? 1 : 0;
  card.classList.toggle("selected");
  updateProgress();
}

function updateProgress() {
  const answered = selected.filter(v => v === 1).length;
  progress.style.width = `${(answered / features.length) * 100}%`;
}

document.getElementById("quiz-form").addEventListener("submit", e => {
  e.preventDefault();

  if (!selected.includes(1)) {
    alert("Please select at least one interest!");
    return;
  }

  fetch("http://localhost:5000/predict", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, email, answers: selected })
  })
    .then(res => res.json())
    .then(data => {
      const attempts = JSON.parse(localStorage.getItem("careerAttempts")) || [];
      attempts.push({ username, email, career: data.career, date: new Date().toLocaleString() });
      localStorage.setItem("careerAttempts", JSON.stringify(attempts));
      quizContainer.classList.add("hidden");
      finishContainer.classList.remove("hidden");
    })
    .catch(() => alert("Server error. Please try again."));
});

dashboardBtn.addEventListener("click", () => {
  window.location.href = "dashboard.html";
});
