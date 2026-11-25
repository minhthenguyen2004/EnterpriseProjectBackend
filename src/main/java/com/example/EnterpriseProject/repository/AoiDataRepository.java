package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.AoiData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AoiDataRepository extends JpaRepository<AoiData, Long> {

    List<AoiData> findByIdSnDetail(Long idSnDetail);

    List<AoiData> findBySerialNumber(String serialNumber);

    List<AoiData> findByCarrierCode(String carrierCode);
}

