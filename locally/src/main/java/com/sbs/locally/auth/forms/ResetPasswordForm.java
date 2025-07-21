package com.sbs.locally.auth.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordForm {

	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 20, message="비밀번호는 8자 이상, 20자 이하입니다.")
	private String password1;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 20, message="비밀번호는 8자 이상, 20자 이하입니다.")
	private String password2;
	
	@NotBlank(message = "유효하지 않은 접근입니다.")
	private String passwordToken;
}
