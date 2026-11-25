package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ExtentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtentDataRepository extends JpaRepository<ExtentData, Long> {

    List<ExtentData> findByIdSnDetail(Long idSnDetail);

    List<ExtentData> findBySerialNumber(String serialNumber);

    List<ExtentData> findByCarrierCode(String carrierCode);
}

