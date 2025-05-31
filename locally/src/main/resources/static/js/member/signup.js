const signupForm = document.querySelector('#signupForm');

signupForm.onsubmit = function(event) {
	alert("실험중");
	
	if (!signupForm.email.value || !signupForm.email.value.trim() || 
	    !signupForm.password1.value || !signupForm.password1.value.trim() ||
		!signupForm.password2.value || !signupForm.password2.value.trim() || 
		!signupForm.nickname.value || !signupForm.nickname.value.trim()) {
		alert("빈칸을 채워주세요.");
		event.preventDefault();
		return false;
	}

}

