package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity

// WebSecurityConfigurerAdapter 상속받아 환경설정
// WebConfig에 SecurityConfig.class 등록해야함
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 비밀번호 암호화 객체 PasswordEncoder
	// 빈 생성해야함
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// SuccessHandler (사용자 커스텀 생성) 
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler(); // 아직 생성안함
	}
	
	// FailureHandler (사용자 커스텀 생성)
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
	}
	
	// UserDetail (사용자 커스텀 생성)
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService(); // 아직 생성안함
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증되는 객체 설정
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소 맵핑 설정
		// cstf() 공격에 대한 설정 막기
		http.csrf().disable();
		
		// 승인 요청
		// antMatchers : 접근을 허용하는 값
		// permitAll() : 누구나 접근 가능한 경로
		// authenticated() : 인증된 사용자만 가능
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")
		.antMatchers("/" , "/board/list" , "/board/detail" , "/comment/**" , 
				     "/upload/**" , "/resources/**" , "/member/register" , "/member/login").permitAll()
		.anyRequest().authenticated();
		
		// 커스텀 로그인 페이지를 구성
		// controller에 주소 요청 맵핑이 같이 있어야함
		http.formLogin()
		.usernameParameter("email") // 아이디가되는 변수명
		.passwordParameter("pwd") // 비밀번호가 되는 변수명
		.loginPage("/member/login") // 로그인이될 페이지
		.successHandler(authSuccessHandler()) // 로그인 성공
		.failureHandler(authFailureHandler()); // 로그인 실패
		
		// 로그아웃 페이지
		// 반드시 metgod = "post"
		http.logout()
		.logoutUrl("/member/logout") // 로그아웃이될 페이지
		.invalidateHttpSession(true) // 세션끊기
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/"); //로그아웃이된후 가는 페이지
	}

	
	
}