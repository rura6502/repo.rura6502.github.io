package io.github.rura6502.authorization_server.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findOneByLoginUsername(String loginusername);
}