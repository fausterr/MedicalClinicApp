package b2c.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import b2c.app.model.PermissionForm;

@Repository
public interface PermissionFormRepo extends JpaRepository<PermissionForm, Long>{

	public PermissionForm findByPesel(long pesel);
}