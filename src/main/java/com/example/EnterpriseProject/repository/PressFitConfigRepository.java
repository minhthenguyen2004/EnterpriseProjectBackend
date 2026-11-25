package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.PressFitConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressFitConfigRepository extends JpaRepository<PressFitConfig, Long> {

    List<PressFitConfig> findByProductNameAndLineName(String productName, String lineName);

    List<PressFitConfig> findByProductNameAndProductTypeAndStep(String productName, String productType, Integer step);
}

