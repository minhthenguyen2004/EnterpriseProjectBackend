package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ErrorConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErrorConfigRepository extends JpaRepository<ErrorConfig, Integer> {

    Optional<ErrorConfig> findByErrorKey(Integer errorKey);

    Optional<ErrorConfig> findByCodeValue(Integer codeValue);

    List<ErrorConfig> findByDescriptionContainingIgnoreCase(String keyword);
}
