package com.surya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class CutomAuhthenticationProvider implements AuthenticationProvider {
@Autowired
UserDetailsService uds;
@Autowired
PasswordEncoder pe;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String uname=authentication.getName();
		UserDetails ud=uds.loadUserByUsername(uname);
		if(ud!=null)
		{
			if(pe.matches((CharSequence) authentication.getCredentials(), ud.getPassword()))
			{
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), authentication.getAuthorities());
				return authenticationToken;
			}
			
		}
		throw new BadCredentialsException("Error!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
