package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.formation.security.AuthService;

@EnableWebSecurity
@ComponentScan("fr.formation.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthService authService;
	
	//A UTILISER SI PAS DE SCAN ET PAS D'INJECTION (DE DEPENDANCE)
//	public AuthService authService() {
//		return new AuthService();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/**").hasAnyRole("ADMIN", "USER")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/home", true)
				.failureUrl("/login?error=true")
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
		return new BCryptPasswordEncoder();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin123456").roles("ADMIN")
//			.and()
//			.withUser("user").password("{noop}user123456").roles("USER");
//	}
}