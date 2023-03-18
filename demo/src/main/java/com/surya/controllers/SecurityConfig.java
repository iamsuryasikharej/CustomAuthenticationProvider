package com.surya.controllers;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.surya.userdetailsconfig.CustomUserDetailsService;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource ds;
	@Autowired
	@Qualifier("encoder")
	@Lazy
	PasswordEncoder e;
	@Lazy
	@Autowired
	CutomAuhthenticationProvider cap;
//	@Bean
//	public JdbcUserDetailsManager getUserDetailsService()
//	{
////		User.withUsername("surya").password("{bcrypt}$2a$10$pZXMvFenJ33jWLRvGieiGOyS9YFmFqIlI6lAPSCR8uosW0ZMTRKlq").authorities("ADMIN").build();
////		return new InMemoryUserDetailsManager(User.withUsername("surya").password(e.encode("surya")).authorities("ADMIN").build());
//		return new JdbcUserDetailsManager(ds);
//		
//	}
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		InMemoryUserDetailsManager detailsService=new InMemoryUserDetailsManager();
		UserDetails details=User.withUsername("admin")
				.password("admin").authorities("admin").build();
		detailsService.createUser(details);
		return detailsService;
		
				}
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		
		return NoOpPasswordEncoder.getInstance();
	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.csrf().disable();
//		http.authorizeHttpRequests().antMatchers("/adduser").permitAll()
//		.anyRequest().authenticated();
//
//	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(cap);
	}
	
	
	
	

}
