function handleLogin(event) {
    event.preventDefault();

    // Get user input
    const email = document.getElementById("username").value;
    const password = document.getElementById("motDePasse").value;

    const user = {
        username: email,
        password: password
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
        }else{
             fetch('http://localhost:8080/gestiondetache/v1/user/findByUsername/'+user.username, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(response => {
                    if(!response.ok){
                        alert(' Cet utilisateur n\'existe pas donc n\'a pas été trouvé');
                    }
                     return response.json();
                }).then(data => {
                    localStorage.setItem('connectedUser', JSON.stringify(data));
                    window.location.href = 'index.html';
                }).catch(error => {
                    console.error('GET request error', error);
                });
            //
        }
        return response.json();
    }).catch(error => {
        console.error('POST request error', error);
    });
}



const loginForm = document.getElementById("loginForm");
loginForm.addEventListener("submit", handleLogin);