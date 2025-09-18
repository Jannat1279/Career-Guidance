const container = document.getElementById('all-attempts');

document.getElementById('darkToggle').addEventListener('change', () => {
  document.body.classList.toggle('dark-mode');
});

const attempts = JSON.parse(localStorage.getItem('careerAttempts')) || [];

if (attempts.length === 0) {
  container.innerHTML = '<p>No attempts found.</p>';
} else {
  attempts.forEach(attempt => createCard(attempt, container));
}

function createCard(attempt, container) {
  const card = document.createElement('div');
  card.className = 'question-box';
  card.innerHTML = `
    <p><strong>Name:</strong> ${attempt.username}</p>
    <p><strong>Email:</strong> ${attempt.email || 'N/A'}</p>
    <p><strong>Career:</strong> ${attempt.career}</p>
    <p><strong>Date:</strong> ${attempt.date}</p>
    <button class="btn" onclick="deleteAttempt('${attempt.date}')">Delete</button>
  `;
  container.appendChild(card);
}

function deleteAttempt(date) {
  let list = JSON.parse(localStorage.getItem('careerAttempts')) || [];
  list = list.filter(a => a.date !== date);
  localStorage.setItem('careerAttempts', JSON.stringify(list));
  location.reload();
}
