const menuButton = document.querySelector(".menu-toggle");
const navLinks = document.querySelector(".nav-links");
const links = document.querySelectorAll(".nav-links a");
const form = document.querySelector("#reservation-form");
const formMessage = document.querySelector("#form-message");

menuButton.addEventListener("click", () => {
    const isOpen = navLinks.classList.toggle("open");
    menuButton.setAttribute("aria-expanded", String(isOpen));
});

links.forEach((link) => {
    link.addEventListener("click", () => {
        navLinks.classList.remove("open");
        menuButton?.setAttribute("aria-expanded", "false");
    });
});

const sections = [...document.querySelectorAll("main section[id]")];

function updateActiveLink() {
    let currentSection = null;

    for (let i = 0; i < sections.length; i++) {
        const section = sections[i];
        const top = section.getBoundingClientRect().top;

        if (top <= 130) {
            currentSection = section;
        }
    }

    for (let i = 0; i < links.length; i++) {
        const link = links[i];
        const href = link.getAttribute("href");

        if (currentSection && href === "#" + currentSection.id) {
            link.classList.add("active");
        } else {
            link.classList.remove("active");
        }
    }
}

window.addEventListener("scroll", updateActiveLink);
window.addEventListener("load", updateActiveLink);