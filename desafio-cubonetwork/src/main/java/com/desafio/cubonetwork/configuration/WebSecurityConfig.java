package com.desafio.cubonetwork.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//	@Autowired
//	private AuthenticationService authenticationService;
//	@Autowired
//	private FilterToken filter;

//	@Bean
//    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
//        return new MvcRequestMatcher.Builder(introspector);
//    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
//        		AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(authenticationService).passwordEncoder(
//        		this.passwordEncoder());
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        return http
				.cors(withDefaults())
				.authorizeHttpRequests((auth) -> auth
//						.requestMatchers(
//								antMatcher("/h2-console/**/**"),
//
//								antMatcher("/api/signin"), 
//								antMatcher("/api/logout"), 
//								antMatcher("/api/users"), 
//								antMatcher("/api/users/**"), 
//								antMatcher("/css/**"), 
//								antMatcher("/icons/**"), 
//								antMatcher("/image"),
//								antMatcher("/image/**"), 
//								antMatcher("/js/**"), 
//								antMatcher("/plugins/**") 
//								)
//						.permitAll()
						.anyRequest()
						.permitAll())
//				.formLogin(form -> form
//						.loginPage("/login")
//						.defaultSuccessUrl("/index", true)
//						.permitAll())
//				.logout(logout -> logout
//						.logoutUrl("/api/logout")
//						.logoutSuccessUrl("/login")
//						.invalidateHttpSession(true))
//				.cors((cors) -> cors
//						.disable())
				.csrf((csrf) -> csrf
						.disable())
//				.authenticationManager(authenticationManager)
				.headers((headers) -> headers
	            		.contentTypeOptions(withDefaults())
	            		.frameOptions(withDefaults())
	            		.disable())
//				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}