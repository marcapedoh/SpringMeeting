function handleTaskCollection(event){
    event.preventDefault();

    fetch('http://localhost:8080/gestiondetache/v1/task/all' , {
        method: 'GET'
    }).then(response => {
        if(!response.ok){
            throw new Error('un problème est survenu lors de la recupération');
        }
        return response.json();
    }).then(data => {
        const tableBody= document.getElementById('table-body');
        const dataList= data;

        dataList.forEach(ligne =>{
            const row = document.createElement('tr');


            const idCell= document.createElement('th');
            idCell.scope='row';
            idCell.textContent=ligne.id;
            row.appendChild(idCell);

            const descriptionCell= document.createElement('td');
            descriptionCell.textContent= ligne.description;
            row.appendChild(descriptionCell);

            const dateTask= document.createElement('td');
            dateTask.textContent= ligne.dateTask;
            row.appendChild(dateTask);

            tableBody.appendChild(row);
        });
    }).catch(error => {
         console.error('GET request error:', error);
    })
}

const tableBodyElement = document.getElementById('table-body');
window.addEventListener("load", handleTaskCollection)
