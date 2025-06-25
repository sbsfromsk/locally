const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", validateLoginForm);

function validateLoginForm(event) {
	const email = loginForm.email.value.trim();
	const password = loginForm.password.value.trim();
	
	if(!email || !password) {
		event.preventDefault();
		
		alert("아이디 비밀번호를 입력해주세요.");
	}
}