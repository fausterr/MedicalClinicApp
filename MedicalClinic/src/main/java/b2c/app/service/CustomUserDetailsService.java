package b2c.app.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import b2c.app.model.User;
import b2c.app.model.UserRole;
import b2c.app.repository.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepo userRepo;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByLogin(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		
		org.springframework.security.core.userdetails.User userDetails = 
				new org.springframework.security.core.userdetails.User(
						user.getLogin(),
						user.getPassword(),
						convertAuthorities(user.getRoles()));
		return userDetails;
	}

	private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (UserRole role: userRoles) 
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}
}