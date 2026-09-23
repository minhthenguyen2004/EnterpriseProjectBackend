package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCardId(String cardId);

    Optional<User> findByUsername(String username);

    boolean existsByCardId(String cardId);

    boolean existsByUsername(String username);
}
