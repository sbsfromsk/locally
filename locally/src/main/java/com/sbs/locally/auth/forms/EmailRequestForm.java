package com.sbs.locally.auth.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestForm {

	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "올바른 이메일 형식을 입력해주세요.")
	private String email;
	
}
