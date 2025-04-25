const slider = document.querySelector('.slider');
const slides = document.querySelectorAll('.slide');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');

let currentSlide = 0;

function showSlide(n) {
    slider.style.transform = `translateX(-${n * 100}%)`;
    slides.forEach((slide, index) => {
        slide.classList.toggle('active', index === n);
    });
}

function nextSlide() {
    currentSlide = (currentSlide + 1) % slides.length;
    showSlide(currentSlide);
}

function prevSlide() {
    currentSlide = (currentSlide - 1 + slides.length) % slides.length; // Handle negative indices
    showSlide(currentSlide);
}

nextBtn.addEventListener('click', nextSlide);
prevBtn.addEventListener('click', prevSlide);

// Auto slide (optional)
let autoSlideInterval;
function startAutoSlide() {
    autoSlideInterval = setInterval(nextSlide, 3000); // Change interval as needed
}

function stopAutoSlide() {
    clearInterval(autoSlideInterval);
}

// Start auto slide when page loads (optional):
startAutoSlide();

// Stop auto slide on user interaction (optional, improves UX):
slider.addEventListener('mouseenter', stopAutoSlide);
slider.addEventListener('mouseleave', startAutoSlide);


showSlide(currentSlide); // Show initial slide


//faq dropdown

const accordians = document.querySelectorAll('.accordian');

accordians.forEach(accordian => {
    const icon = accordian.querySelector('.icon');
    const answer = accordian.querySelector('.answer');

    accordian.addEventListener('click', ()=>{

        icon.classList.toggle('active');
        answer.classList.toggle('active');
    })
});

//contact us
const inputs = document.querySelectorAll(".input");

function focusFunc(){
    let parent = this.parentNode;
    parent.classList.add("focus");
}
function blurFunc(){
    let parent = this.parentNode;
    if(this.value == ""){
        parent.classList.remove("focus");
    }
    
}

inputs.forEach(input =>{
    input.addEventListener("focus", focusFunc());
    input.addEventListener("blur", blurFunc());
});
