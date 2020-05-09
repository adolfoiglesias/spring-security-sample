package com.calarix.security.repository;

import com.calarix.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUserOrEmail(String user, String email);

    public boolean existsByUser(String user);
    public boolean existsByEmail(String email);
}
