const signupForm = document.querySelector('#signupForm');

signupForm.addEventListener("submit", function(event) {
	// => signupForm.onSubmit = function(event) { ... };

	const formData = {
		email: signupForm.email.value?.trim() || '',
		password1: signupForm.password1.value?.trim() || '',
		password2: signupForm.password2.value?.trim() || '',
		nickname: signupForm.nickname.value?.trim() || ''
	};

	if (!validateSignup(formData)) {
		event.preventDefault();

		return;
	};

	if (!validateEmail(formData)) {
		event.preventDefault();

		return;
	}

	if (!validatePassword(formData)) {
		event.preventDefault();

		return;
	}
});

function validateSignup(formData) {
	const { email, password1, password2, nickname } = formData;

	if (!email || !password1 || !password2 || !nickname) {
		alert("빈칸을 채워주세요.");
		return false;
	}

	return true;
}

function validateEmail(formData) {
	const { email } = formData;

	if (!email.includes("@")) {
		alert("올바른 이메일 형식을 입력해주세요.");
		return false;
	}

	return true;
}

function validatePassword(formData) {
	const { password1, password2 } = formData;

	if (password1.length < 8) {
		alert("비밀번호는 8자 이상이어야 합니다.");
		return false;
	}

	if (password1 !== password2) {
		alert("비밀번호가 일치하지 않습니다.");;
		return false;
	}

	return true;
}



