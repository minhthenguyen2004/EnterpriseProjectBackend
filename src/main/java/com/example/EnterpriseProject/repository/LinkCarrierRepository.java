package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.LinkCarrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkCarrierRepository extends JpaRepository<LinkCarrier, Long> {

    Optional<LinkCarrier> findByCarrierCode(String carrierCode);

    List<LinkCarrier> findBySerialNumber(String serialNumber);

    Optional<LinkCarrier> findByIpAddress(String ipAddress);

    Optional<LinkCarrier> findByCarrierCodeAndStep(String carrierCode, Integer step);
}
