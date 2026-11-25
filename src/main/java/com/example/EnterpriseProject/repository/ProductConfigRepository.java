package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ProductConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductConfigRepository extends JpaRepository<ProductConfig, Long> {

    List<ProductConfig> findByProductNameAndStep(String productName, Integer step);

    List<ProductConfig> findByProductNameAndProductTypeAndStep(String productName, String productType, Integer step);

    List<ProductConfig> findByProductNameAndProductTypeAndStepAndGroupName(
            String productName, String productType, Integer step, String groupName);

    List<ProductConfig> findByProductNameAndProductTypeAndStepAndIsActiveTrue(
            String productName, String productType, Integer step);
}
