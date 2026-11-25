package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.PressFitData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressFitDataRepository extends JpaRepository<PressFitData, Long> {

    List<PressFitData> findByIdSnDetail(Long idSnDetail);

    List<PressFitData> findBySerialNumber(String serialNumber);

    List<PressFitData> findByCarrierCode(String carrierCode);
}

