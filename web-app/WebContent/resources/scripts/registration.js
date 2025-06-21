function getContextPath() {
    const appRoot = document.getElementById('app-root');
    return appRoot ? appRoot.getAttribute('data-context-path') : '';
}

function checkExistence() {
    const email = document.getElementById("email").value;
    if (email) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", getContextPath() + "/MatchEmail", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                const exists = response.Exists;
                document.getElementById("error-mailExists").style.display = exists ? "block" : "none";
                document.getElementById('invio').disabled = exists;
            }
        };
        xhr.send("email=" + encodeURIComponent(email));
    } else {
        document.getElementById("error-mailExists").style.display = "none";
        document.getElementById('invio').disabled = false;
    }
}

function passwordMatch() {
    const password = document.getElementById("password-one").value;
    const confirm = document.getElementById("password-two").value;
    if (password !== confirm) {
        document.getElementById("error-match").style.display = "block";
        return false;
    } else {
        document.getElementById("error-match").style.display = "none";
        return true;
    }
}

function validate(event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const surname = document.getElementById("surname").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password-one").value;
    const confirm = document.getElementById("password-two").value;
    const date = document.getElementById("date").value;
    let isValid = true;

    function validateEmail(email) {
        const emailRegEx = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailRegEx.test(email);
    }

    function validatePassword(password) {
        if (password.length < 8) return false;
        if (!/[A-Z]/.test(password)) return false;
        if (!/[a-z]/.test(password)) return false;
        if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) return false;
        if (/\s/.test(password)) return false;
        return true;
    }

    function validateWord(word) {
        const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ' ]{3,50}$/;
        return regex.test(word.trim());
    }

    function validateDate(dateString) {
        if (!/^\d{4}-\d{2}-\d{2}$/.test(dateString)) return false;
        const date = new Date(dateString);
        if (isNaN(date.getTime())) return false;
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        return date <= today;
    }

    if (!validateWord(name)) {
        isValid = false;
        document.getElementById("error-name").style.display = "block";
    } else {
        document.getElementById("error-name").style.display = "none";
    }

    if (!validateWord(surname)) {
        isValid = false;
        document.getElementById("error-surname").style.display = "block";
    } else {
        document.getElementById("error-surname").style.display = "none";
    }

    if (!validateEmail(email)) {
        isValid = false;
        document.getElementById("error-mailValid").style.display = "block";
    } else {
        document.getElementById("error-mailValid").style.display = "none";
    }

    if (!validatePassword(password)) {
        isValid = false;
        document.getElementById("error-password").style.display = "block";
    } else {
        document.getElementById("error-password").style.display = "none";
    }

    if (password !== confirm) {
        isValid = false;
        document.getElementById("error-match").style.display = "block";
    } else {
        document.getElementById("error-match").style.display = "none";
    }

    if (!validateDate(date)) {
        isValid = false;
        document.getElementById("error-date").style.display = "block";
    } else {
        document.getElementById("error-date").style.display = "none";
    }

    if (document.getElementById("invio").disabled) {
        isValid = false;
    }

    if (isValid) {
        event.target.submit();
    }

    return isValid;
}
