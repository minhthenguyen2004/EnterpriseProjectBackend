package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.SnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnDetailRepository extends JpaRepository<SnDetail, Long> {

    List<SnDetail> findBySerialNumber(String serialNumber);

    List<SnDetail> findByCarrierCode(String carrierCode);

    List<SnDetail> findBySerialNumberAndStep(String serialNumber, Integer step);

    List<SnDetail> findByCarrierCodeAndStep(String carrierCode, Integer step);

    List<SnDetail> findByProductNameAndLineName(String productName, String lineName);

    Optional<SnDetail> findFirstBySerialNumberOrderByInStationTimeDesc(String serialNumber);

    Optional<SnDetail> findFirstByCarrierCodeOrderByInStationTimeDesc(String carrierCode);
}
