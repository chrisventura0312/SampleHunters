const showPasswordRegisterCheckbox = document.getElementById('showPasswordRegister');
const passwordRegisterInput = document.getElementById('passwordRegister');

showPasswordRegisterCheckbox.addEventListener('change', function () {
    if (showPasswordRegisterCheckbox.checked) {
        passwordRegisterInput.type = 'text';
    } else {
        passwordRegisterInput.type = 'password';
    }
});

const showPasswordLoginCheckbox = document.getElementById('showPasswordLogin');
const passwordLoginInput = document.getElementById('passwordLogin');

showPasswordLoginCheckbox.addEventListener('change', function () {
    if (showPasswordLoginCheckbox.checked) {
        passwordLoginInput.type = 'text';
    } else {
        passwordLoginInput.type = 'password';
    }
});

const showConfirmPasswordCheckbox = document.getElementById('showConfirmPassword');
const confirmPasswordInput = document.getElementById('confirm');

showConfirmPasswordCheckbox.addEventListener('change', function () {
    if (showConfirmPasswordCheckbox.checked) {
        confirmPasswordInput.type = 'text';
    } else {
        confirmPasswordInput.type = 'password';
    }
});