document.querySelector('#logout').addEventListener('click', logout);

function logout(){
    fetch('http://localhost:7000/logout', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies, you should include them in future requests. So in our case, it's important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/loginPage.html';
        } else if (response.status === 401) {
            displayInvalidLogout();
        }
    })
}

function displayInvalidLogout() {
    let bodyElement = document.querySelector('body');

    let pElement = document.createElement('p');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid logout! No one was logged in!';

    bodyElement.appendChild(pElement);
}