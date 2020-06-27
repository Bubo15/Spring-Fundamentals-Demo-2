const conditions = {
    moreThanBoard: (target, board) => target.value.length > board,
    betweenBoardUpAndDown: (target, boardUp, boardDown) => target.value.length >= boardDown && target.value.length <= boardUp,
    budgetMoreThan: (target, board) => target.value >= board
};

document.addEventListener('DOMContentLoaded', () => {
    const name = getVariable('#name');
    const town = getVariable('#town');
    const description = getVariable('#description');
    const budget = getVariable('#budget');

    this.makeAllErrorsBlock();

    const button = getVariable('button[type=submit]');
    button.disabled = true;

    const isConditionPass = (type, target, boardUp, boardDown) => {
        const small = target.parentNode.querySelector('small').style;
        small.display = conditions[type](target, boardUp, boardDown) ? 'none' : 'block';
        button.disabled = isButtonActive();
    };

    name.addEventListener('keyup', (e) => isConditionPass("betweenBoardUpAndDown", e.target, 10, 2));
    name.addEventListener('keyup', (e) => isCompanyExist(e.target.value, button));
    town.addEventListener('keyup', (e) => isConditionPass("betweenBoardUpAndDown", e.target, 10, 2));
    description.addEventListener('keyup', (e) => isConditionPass("moreThanBoard", e.target, 10));
    budget.addEventListener('keyup', (e) => isConditionPass("budgetMoreThan", e.target, 0));
});

function isButtonActive() {
    const allBlock = ([...document.querySelectorAll('small')].map(x => x.style.display).filter(x => x === 'block'));
    return allBlock.length !== 0;
}

function makeAllErrorsBlock() {
    [...document.querySelectorAll('small')].forEach(s => {
        if (s.id === "already-exist") {
            s.style.display = 'none'
        } else {
            s.style.display = 'block'
        }
    });
}

function getVariable(variableName) {
    return document.querySelector(variableName);
}

async function isCompanyExist(name, button) {
    await fetch('http://localhost:8000/companies/company/' + name)
        .then(date => date.json())
        .then(response => {
            if (response.status !== 404) {
                console.log(response)
                const small = document.getElementById('already-exist').style;
                small.display = response ? 'block' : 'none';
                button.disabled = isButtonActive();
            }
        });
}


