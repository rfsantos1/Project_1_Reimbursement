document.querySelector('#reimbursements').addEventListener('click', reimbursement);
document.querySelector('#addReimbursements').addEventListener('click', addReimbursements);

function reimbursement(){
	window.location.href = "ReimbursementTable.html";
}

function addReimbursements(){
	window.location.href = "addReimbursement.html";
}