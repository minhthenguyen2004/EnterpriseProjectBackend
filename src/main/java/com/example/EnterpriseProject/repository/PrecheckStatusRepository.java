package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.PrecheckStatus;
import com.example.EnterpriseProject.enums.BooleanFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecheckStatusRepository extends JpaRepository<PrecheckStatus, Long> {

    Optional<PrecheckStatus> findByCarrierCode(String carrierCode);

    Optional<PrecheckStatus> findByCarrierCodeAndStep(String carrierCode, Integer step);

    Optional<PrecheckStatus> findByIpAddress(String ipAddress);

    List<PrecheckStatus> findByLineNameAndStep(String lineName, Integer step);

    List<PrecheckStatus> findByCarrierCodeAndIsPreCheck(String carrierCode, BooleanFlag isPreCheck);
}
