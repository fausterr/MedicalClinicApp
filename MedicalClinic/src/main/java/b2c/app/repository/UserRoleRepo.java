package b2c.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import b2c.app.model.UserRole;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long>{

	UserRole findByRole(String role);
}