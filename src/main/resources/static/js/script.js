function handleLogin(event) {
    event.preventDefault();

    // Get user input
    const email = document.getElementById("username").value;
    const password = document.getElementById("motDePasse").value;

    const user = {
        username: email,
        motDePasse: password
    };

    fetch('http://localhost:8080/gestiondetache/v1/auth/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
        if (!response.ok) {
            alert('Login and / or password is incorrect');
        }
        return response.json();
    }).then((response) => {
        if(response.ok){
            localStorage.setItem('connectedUser', JSON.stringify(response));
            window.location.href = 'index.html'
        }
    }).catch(error => {
        console.error('POST request error', error);
    });
}



const loginForm = document.getElementById("loginForm");
loginForm.addEventListener("submit", handleLogin);