package annov4.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import annov4.crud.crud.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
