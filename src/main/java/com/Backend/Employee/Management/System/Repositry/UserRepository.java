package com.Backend.Employee.Management.System.Repositry;

import com.Backend.Employee.Management.System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByResetToken(String resetToken);

}
