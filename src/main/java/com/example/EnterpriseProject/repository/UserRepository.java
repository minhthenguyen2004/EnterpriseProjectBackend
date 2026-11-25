package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmployeeId(String employeeId);

    Optional<User> findByUsername(String username);

    boolean existsByEmployeeId(String employeeId);

    boolean existsByUsername(String username);

    default Optional<User> findByCardId(String cardId) {
        return findByEmployeeId(cardId);
    }

    default boolean existsByCardId(String cardId) {
        return existsByEmployeeId(cardId);
    }
}
