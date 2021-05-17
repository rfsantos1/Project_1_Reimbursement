document.querySelector('#submit').addEventListener('click', request);
let currentUser;

let typeForReimbiursement = {
	"other":{},
	"traveling":{},
	"food":{},
	"lodging":{}
	}

window.onload = function() {
	let type = document.getElementById("type");
	for(let x in typeForReimbiursement){
		type.options[type.options.length] = new Option(x, x);
	}
	renderCurrentUser();
	
	let currentUser = JSON.parse(sessionStorage.getItem("LoggedInUser"));
	if(currentUser){
		
	}else{
		window.location.href = "loginPage.html";
	}
}

function request() {
    let am = document.querySelector('#amount').value;
    let desc = document.querySelector('#description').value;
    let employ = currentUser;
    let myReimbursementType = document.querySelector('#type').value;

    let data = {
        amount: am,
        description: desc,
        employee: employ,
        reimbursementType: myReimbursementType
    };

    fetch('http://localhost:7000/addReimbursement', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/employee.html';
        }
    })
}

function renderCurrentUser() {
    fetch('http://localhost:7000/current_user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
        }

        return response.json();
    }).then((data) => {
	
		sessionStorage.setItem("LoggedInUser", JSON.stringify(data));
    })
}

