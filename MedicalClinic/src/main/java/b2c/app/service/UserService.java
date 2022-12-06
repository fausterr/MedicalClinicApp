package b2c.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import b2c.app.model.User;
import b2c.app.model.UserRole;
import b2c.app.repository.UserRepo;
import b2c.app.repository.UserRoleRepo;

@Service
public class UserService {

	private static final String DEFAULT_ROLE = "ROLE_USER";
	private UserRepo userRepo;
	private UserRoleRepo userRoleRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired 
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Autowired
	public void setUserRoleRepo(UserRoleRepo userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}
	
	public static String getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		String login = context.getAuthentication().getName();
		return login;
	}
	
	public User getUserByLogin(String login) {
		User user = userRepo.findByLogin(login);
		return user;
	}
	
	public User getUserByPesel(long pesel) {
		User user = userRepo.findByPesel(pesel);
		return user;
	}
	
	public void deleteByPesel(long pesel) {
		userRepo.deleteByPesel(pesel);
	}
	
	public static boolean hasRole (String roleName) {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}
	
	public void addDefaultUser(User user) {
		UserRole userDefaultRole = userRoleRepo.findByRole(DEFAULT_ROLE);
		user.getRoles().add(userDefaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepo.save(user);
	}
}