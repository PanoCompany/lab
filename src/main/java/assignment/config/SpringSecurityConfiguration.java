package assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final UserPrincipalDetailService userPrincipalDetailService;

	public SpringSecurityConfiguration(UserPrincipalDetailService userPrincipalDetailService) {
		this.userPrincipalDetailService = userPrincipalDetailService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/register","/login","/resources/**").permitAll()
			.antMatchers("/manager/**").hasAnyRole("ADMIN")
			.antMatchers("/course/**").hasAnyRole("TEACHER", "STUDENT", "ADMIN")
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.and()
			.rememberMe().tokenValiditySeconds(30000).key("WhatEver!")
			.rememberMeParameter("checkRememberMe");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
