function handleRegistration(event) {
    event.preventDefault();

    // Get user input
    const description = document.getElementById("description").value;
    const dateTask = document.getElementById("dateTask").value;
    const user=localStorage.getItem('connectedUser');
    // Create an object with user information
    const Task = {
        description: description,
        dateTask: dateTask,
        idUser: user.id
    };
    fetch('http://localhost:8080/gestiondetache/v1/task/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Task)
    }).then(response => {
        if (!response.ok) {
            throw new Error('un problème est survenue! lors de la creation');
        }else{
            window.location.href="#TaskTable";
        }
        return response.json();
    }).catch(error => {
        console.error('POST request error:', error);
    });

}

const registrationForm = document.getElementById("registrationForm");
registrationForm.addEventListener("submit", handleRegistration);
