package b2c.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean("authenticationManager")
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	     return super.authenticationManagerBean();
	 }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
	
	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("{noop}admin").roles("SUPERADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/a/**").hasAnyRole("ADMIN", "SUPERADMIN")
			.antMatchers("/sa/**").hasRole("SUPERADMIN") 
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login_form")
					.permitAll()
				.loginProcessingUrl("/processlogin")
				.usernameParameter("login")
				.passwordParameter("pass")
		.and()
		.logout()
		.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.permitAll();
	}
}