package com.efub.community.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.efub.community.domain.member.auth.service.CustomOAuth2UserService;
import com.efub.community.global.jwt.JwtAuthenticationProvider;
import com.efub.community.global.jwt.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationProvider jwtAuthenticationProvider;
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf().disable()    // csrf 비활성화
			.formLogin().disable()    // form login 비활성화
			.httpBasic().disable()    // http basic authentication 비활성화
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)    // JWT를 사용하므로 session은 사용 X
			.and()
			.authorizeRequests()
			.antMatchers("/auth/**", "/oauth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.addFilterBefore(new JwtFilter(jwtAuthenticationProvider),
				UsernamePasswordAuthenticationFilter.class)    // 필터 추가
			.oauth2Login()
			.userInfoEndpoint()
			.userService(customOAuth2UserService)
			.and()
			.and()
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
