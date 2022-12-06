package b2c.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import b2c.app.model.Test;
import b2c.app.model.User;

@Repository
public interface TestRepo extends JpaRepository<Test, Long>{
	
	List<Test> findAllByUserIdAndActive(Long id, boolean active);
	
	long deleteByOrderNumber(long order);
	
	public Test findByOrderNumber(long orderNumber);
	
	List<Test> findAllByUser(User user);
}