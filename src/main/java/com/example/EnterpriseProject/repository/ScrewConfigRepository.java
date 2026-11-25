package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ScrewConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrewConfigRepository extends JpaRepository<ScrewConfig, Long> {

    List<ScrewConfig> findByProductNameAndLineName(String productName, String lineName);

    List<ScrewConfig> findByProductNameAndProductTypeAndStep(String productName, String productType, Integer step);
}

