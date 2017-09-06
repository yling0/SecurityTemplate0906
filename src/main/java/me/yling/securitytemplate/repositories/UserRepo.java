package me.yling.securitytemplate.repositories;

import me.yling.securitytemplate.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail (String email);
    Long countByEmail (String email);
    Long countByUsername (String username);
}
