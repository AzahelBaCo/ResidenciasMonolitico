package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/css/**")
				.permitAll().requestMatchers("/", "/registro**").permitAll()
				
				
				/* AsesorInt */
				.requestMatchers("/asesirin/**").hasRole("ADMIN")
				/* AsesorEx */
				.requestMatchers("/asesirex/**").hasRole("ADMIN")
				/* Convenio */
				.requestMatchers("/convenio/**").hasRole("ADMIN")
				/* Empresa */
				.requestMatchers("/empresa/**").hasRole("ADMIN")
				/* Escuela */
				.requestMatchers("/escuela/**").hasRole("ADMIN")
				/* Estudiante */
				.requestMatchers("/estudiante/**").hasRole("ADMIN")
				/* Administradores */
				.requestMatchers("/admin/**").hasRole("ADMIN")
				/* Reporte*/
				.requestMatchers("/reporte/**").hasAnyRole("ADMIN","ESTUDIANTE","ASESOR")			
				/* Residencias */
				.requestMatchers("/residencia/**").hasAnyRole("ESTUDIANTE", "ASESOR", "ADMIN")
				

				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
				.logout(logout -> logout.permitAll())
				.exceptionHandling(exception -> exception.accessDeniedPage("/acceso-denegado"));

		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder()) // usa el bean definido abajo
				.and().build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
