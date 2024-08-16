function validateForm() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (username === '' || password === '') {
        alert('Please enter both ID and Password.');
        return false;
    }

    // Example: Check minimum password length
    if (password.length < 8) {
        alert('Password must be at least 8 characters long.');
        return false;
    }

    return true;
}