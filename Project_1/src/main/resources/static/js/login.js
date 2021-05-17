document.querySelector('#login').addEventListener('click', login);

function login() {
    let un = document.querySelector('#username').value;
    let pw = document.querySelector('#password').value;

    let data = {
        username: un,
        password: pw
    };

    fetch('http://localhost:7000/login', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
		if(response.status === 200){
			window.location.href = '/employee.html';
		}else if (response.status === 201) {
			window.location.href = '/financeManager.html';
        }
        else if (response.status === 401) {
            displayInvalidLogin();
        }
    }).then((returnedData) => {
		if(returnedData.response === 200){
			
			window.location.href = '/employee.html';
		}
		if(returnedData.getEmployeeRole().getRole() == "employee"){
			window.location.href = '/employee.html';
		}else if(returnedData.getEmployeeRole().getRole() == "financeManager"){
			window.location.href = '/financeManager.html';
		}else{
			displayInvalidLogin();
		}
	})
}

function displayInvalidLogin() {
    let bodyElement = document.querySelector('body');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login!';

    bodyElement.appendChild(pElement);
}