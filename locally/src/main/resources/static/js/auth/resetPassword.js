function validPassword(formData) {
	const { password1, password2 } = formData;

	if (password1 === '' || password2 === '') {
		alert("비밀번호를 입력해주세요.");
		return false;
	}

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

const resetPasswordForm = document.querySelector("#resetPasswordForm");


/*
	1. 자바스크립트에서 1차 검증
	2. 서버 전달
	3. 오류 있을 시, view로 전달
	4. 오류 없을 시, 비밀번호 변경 후, 변경 알람 전송
*/
resetPasswordForm.addEventListener("submit", function(e) {

	e.preventDefault();

	const formData = {
		password1: resetPasswordForm.password1.value?.trim() || '',
		password2: resetPasswordForm.password2.value?.trim() || ''
	}

	console.log(formData);

	if (!validPassword(formData)) {
		e.preventDefault();

		return;
	}
	
	let token  = document.getElementById("_csrf").getAttribute("value");
	let header = document.getElementById("_csrf_header").getAttribute("value");
	
	fetch('/api/auth/resetPassword', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			[header]: token
		},
		body: JSON.stringify({password1 : "", password2: ""}) // 수정
	})
		.then((response) => {
			if (!response.ok) {
				return response.json().then(errorData => {

					const msg = Array.isArray(errorData) ? errorData[0] : errorData.message;
					console.error("서버 에러 메세지:", errorData);
					throw new Error(msg);


				});
			} else {
				alert("비밀번호가 변경되었습니다!");
				
				location.href="/";
			}


		})
		.catch(error => {
			alert("메일 발송해 실패했습니다: " + error.message);
		})
});

