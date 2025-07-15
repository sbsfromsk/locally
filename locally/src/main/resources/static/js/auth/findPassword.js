// 이메일 유효성 검사
const validateEmail = (e) => {
	e.preventDefault();

	let emailValue = document.getElementById("email").value.trim();

	if (!emailValue) {
		alert("이메일을 입력하세요.");
		return;
	}

	sendEmail(emailValue);

}

// 메일 전송
const sendEmail = (email) => {
	console.log("이메일쓰: " + email);

	let token = document.getElementById("_csrf").getAttribute("value");
	let header = document.getElementById("_csrf_header").getAttribute("value");

	fetch('/api/auth/password', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			[header]: token
		},
		body: JSON.stringify({email: email}) 
	})
		.then((response) => {
			if (!response.ok) {
				return response.json().then(errorData => {
					
					const msg = Array.isArray(errorData) ? errorData.join('\n') : errorData.message ;
					console.error('서버 에러 메시지: ', errorData);
					throw new Error(msg);
				});
			}

			console.log("쿵야하이");
			showSuccessMessage();
		}
		)

		.catch(error => {
			alert('메일 발송에 실패했습니다: ' + error.message); 
		})

}

// 서버에 메일 전송 성공 시 보여주는 함수
const showSuccessMessage = () => {
	const passwordForm = document.querySelector("#findPasswordForm");
	const messageBox = document.querySelector("#messageBox");
	passwordForm.style.display = "none";
	messageBox.style.display = "block";

}

const findPasswordForm = document.getElementById("findPasswordForm");

findPasswordForm.addEventListener("submit", validateEmail);