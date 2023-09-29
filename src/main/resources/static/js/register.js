function handleRegistration(event) {
    event.preventDefault();

    // Get user input
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const nom = document.getElementById("nom").value;
    const prenom = document.getElementById("prenom").value;

    // Create an object with user information
    const user = {
        username: username,
        email: email,
        motDePasse: password,
        nom: nom,
        prenom: prenom
    };
    fetch('http://localhost:8080/gestiondetache/v1/auth/registerUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
        if (!response.ok) {
            throw new Error('un problème est survenue!');
        }else{
            localStorage.setItem("RegisterUser", JSON.stringify(user));
            window.location.href="Login.html";
        }
        return response.json();
    }).catch(error => {
        console.error('POST request error:', error);
    });

}

const registrationForm = document.getElementById("registrationForm");
registrationForm.addEventListener("submit", handleRegistration);
