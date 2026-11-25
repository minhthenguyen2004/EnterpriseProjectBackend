package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.CarrierInspectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierInspectionLogRepository extends JpaRepository<CarrierInspectionLog, Long> {

    List<CarrierInspectionLog> findByCarrierCodeOrderByInspectionTimeDesc(String carrierCode);

    List<CarrierInspectionLog> findByInspector(String inspector);
}

