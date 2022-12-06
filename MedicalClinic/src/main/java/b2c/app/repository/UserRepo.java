package b2c.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import b2c.app.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByLogin(String login);
	
	public User findByPesel(long pesel);
	
	public void deleteByPesel(long pesel);
}