package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.StepConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StepConfigRepository extends JpaRepository<StepConfig, Long> {

    List<StepConfig> findByProductNameAndProductType(String productName, String productType);

    Optional<StepConfig> findByProductNameAndProductTypeAndStep(String productName, String productType, Integer step);

    List<StepConfig> findByLineName(String lineName);
}

