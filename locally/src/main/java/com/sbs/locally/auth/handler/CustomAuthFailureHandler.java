package com.sbs.locally.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("로그인 실패 ㅠㅠ");
		
		String errorMessage = "아이디 또는 비밀번호를 확인해주세요.";
		
		if (exception instanceof DisabledException) {
			errorMessage = "이메일 인증이 완료되지 않았습니다. 메일을 확인해주세요.";
			System.out.println(errorMessage);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('" + errorMessage + "'); location.href='/'</script>");
			out.flush();
			
			return; // 반드시 있어야 아래 redirect 막음!
		}
		
		// redirect
		response.sendRedirect("/auth/login?error");
		
		
		
	}

	
}
