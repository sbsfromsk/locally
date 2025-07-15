const resetPasswordForm = document.querySelector("#resetPasswordForm");

resetPasswordForm.addEventListener("submit", function(e) {
	const formData = {
		password1: resetPasswordForm.password1.value?.trim() || '',
		password2: resetPasswordForm.password2.value?.trim() || ''
	}
	
	if (!validPassword(formData)) {
		e.preventDefault();
		
		return;
	}
});

function validPassword(formData) {
	const {password1, password2} = formData;
	
	if (password1.length < 8) {
		alert("비밀번호는 8자 이상이어야 합니다.");
		return false;
	}
	
	if (password1 !== password2) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	return true;
}