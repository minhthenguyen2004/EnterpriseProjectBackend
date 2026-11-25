package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.UphConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UphConfigRepository extends JpaRepository<UphConfig, Long> {

    List<UphConfig> findByProductName(String productName);

    Optional<UphConfig> findByProductNameAndProductTypeAndLineNameAndStep(
            String productName, String productType, String lineName, Integer step);
}

