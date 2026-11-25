package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.CarrierConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierConfigRepository extends JpaRepository<CarrierConfig, Long> {

    List<CarrierConfig> findByProductNameAndLineName(String productName, String lineName);
}

