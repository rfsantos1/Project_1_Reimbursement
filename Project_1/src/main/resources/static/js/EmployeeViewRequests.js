window.onload = function() {
	createReimbursementTable();
}

function createReimbursementTable() {
	fetch('http://localhost:7000/reimbursements', {
		method: 'GET',
		credentials: 'include'

	}).then((response) => {
		if (response.status === 400) {
			window.location.href = '/employee.html';
		}

		return response.json();
	}).then((data) => {
		console.log(data);
		populateTable(data);
	})
}

function populateTable(response) {
	let tBody = document.querySelector("table tbody");
	tBody.innerHTML = "";
	for (let i = 0; i < response.length; i++) {
		let row = document.createElement("tr");
		for (let key in response[i]) {
			let td = document.createElement("td");
			if (response[i][key] == null) {
				td.innerHTML = "N/A";
			} else if (key == "employee") {
				td.innerHTML = response[i][key].firstName + " " + response[i][key].lastName;
			} else if (key == "financeManager") {
				if (response[i][key].id === null) {
					td.innterHTML = "Null";
				} else {
					td.innerHTML = response[i][key].firstName + " " + response[i][key].lastName;
				}
			} else if (key == "reimbursementStatus") {
				td.innerHTML = response[i][key].status;
			} else if (key == "reimbursementType") {
				td.innerHTML = response[i][key].type;
			} else if (key == null) {
				td.innterHTML = "Null";
			}
			else {
				td.innerHTML = response[i][key];
			}
			row.appendChild(td);
		}
		tBody.appendChild(row);
	}
}