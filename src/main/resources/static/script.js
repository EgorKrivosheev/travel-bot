'use strict';

// If main have scroll add class active-scroll(padding right less on 0.5em)
function isActiveScroll() {
    if (main.clientHeight < main.scrollHeight) {
        main.classList.add('active-scroll');
    } else {
        main.classList.remove('active-scroll');
    }
}

isActiveScroll();

function openAddForm() {
    // TODO: openAddForm()
}

function openEditForm() {
    // TODO: openEditForm()
}
