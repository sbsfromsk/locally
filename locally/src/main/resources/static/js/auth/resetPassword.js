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
const passwordToken = new URLSearchParams(window.location.search).get("token");

console.log("token 값: ", passwordToken);
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
		password2: resetPasswordForm.password2.value?.trim() || '',
		passwordToken: passwordToken
	}

	console.log(formData);

	if (!validPassword(formData)) {
		e.preventDefault();

		return;
	}

	let token = document.getElementById("_csrf").getAttribute("value");
	let header = document.getElementById("_csrf_header").getAttribute("value");

	fetch('/api/auth/resetPassword', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			[header]: token
		},
		body: JSON.stringify(formData)
	})
		.then((response) => {
			
			// 상태코드 200 ~ 299가 아닌 경우
			if (!response.ok) {
				return response.json().then(errorData => {
					console.error("서버 에러 메세지:", errorData);
					throw errorData; // new Error 객체를 만들었을 경우...

				});
			} else { // OK
				alert("비밀번호가 변경되었습니다!");

				location.href = "/";
			}


		})
		.catch(error => {
			if (error.error === "invalid_token") { // error.message로 받아야 함!
				console.log("유효기간이 지났습니다");
				location.href = "/auth/invalidToken";
			} else {
				alert("메일 발송에 실패했습니다: " + (error.message || JSON.stringify(error)));
			}
		})
});

