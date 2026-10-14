package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.Role;
import com.example.EnterpriseProject.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);

    boolean existsByRoleName(RoleName roleName);
}
