package com.sbs.locally.member.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {
	
	@NotBlank(message = "빈칸을 입력해주세요.")
	@Email(message = "올바른 이메일 형식을 입력해주세요.")
	private String email;
	
	@NotBlank(message = "빈칸을 입력해주세요.")
	private String password1;

	@NotBlank(message = "빈칸을 입력해주세요.")
	@Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하입니다.")
	private String password2;
	
	@NotBlank(message = "빈칸을 입력해주세요.")
	private String nickname;
}
