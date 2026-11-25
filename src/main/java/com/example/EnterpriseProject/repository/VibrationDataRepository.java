package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.VibrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VibrationDataRepository extends JpaRepository<VibrationData, Long> {

//    List<VibrationData> findByIdSnDetail(Long idSnDetail);
//
//    List<VibrationData> findBySerialNumber(String serialNumber);
//
//    List<VibrationData> findByCarrierCode(String carrierCode);
}

