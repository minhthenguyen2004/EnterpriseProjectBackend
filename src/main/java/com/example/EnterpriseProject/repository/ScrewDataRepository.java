package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ScrewData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrewDataRepository extends JpaRepository<ScrewData, Long> {

    List<ScrewData> findByIdSnDetail(Long idSnDetail);

    List<ScrewData> findBySerialNumber(String serialNumber);

    List<ScrewData> findByCarrierCode(String carrierCode);
}

