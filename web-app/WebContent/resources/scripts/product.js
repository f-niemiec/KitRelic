document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector(".form");

    form.addEventListener("submit", function (e) {
        let isValid = true;
        const textFields = ["name", "description", "type"];
        textFields.forEach(id => {
            const input = document.getElementById(id);
            const error = document.getElementById(`error-${id}`);
            if (input && input.value.trim() === "") {
                isValid = false;
                error.style.display = "block";
                input.classList.add("input-error");
            } else {
                error.style.display = "none";
                input.classList.remove("input-error");
            }
        });

        const sizeSelected = document.querySelector('input[name="taglia"]:checked');
        const sizeError = document.getElementById("error-size");
        if (!sizeSelected) {
            isValid = false;
            sizeError.style.display = "block";
        } else {
            sizeError.style.display = "none";
        }

        if (!isValid) {
            e.preventDefault();
        }
    });
});

