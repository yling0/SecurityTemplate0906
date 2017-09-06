package me.yling.securitytemplate.repositories;

import me.yling.securitytemplate.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByRole (String role);
}
