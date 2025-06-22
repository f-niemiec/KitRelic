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
                document.getElementById("error-mailExists").style.display = exists ? "none" : "block";
				document.getElementById('invio').disabled = !exists
            }
        };
        xhr.send("email=" + encodeURIComponent(email));
    } else {
        document.getElementById("error-mailExists").style.display = "none";
        document.getElementById('invio').disabled = false;
    }
}

function validatePassword(password) {
    return (
        password.length >= 8 &&
        /[A-Z]/.test(password) &&
        /[a-z]/.test(password) &&
        /[!@#$%^&*(),.?":{}|<>]/.test(password) &&
        !/\s/.test(password)
    );
}

function validateLogin() { 
	const password = document.getElementById("password-one").value; 
	const errorDiv = document.getElementById("error-checked"); 
	if (!validatePassword(password)) { 
		errorDiv.style.display = "block";
		 errorDiv.textContent = "La password non rispetta i criteri richiesti."; 
		 return false; 
	 } 
	 errorDiv.style.display = "none"; 
	 errorDiv.textContent = "";
	 return true;
	 }

