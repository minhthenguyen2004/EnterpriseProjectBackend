package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, String> {

    Optional<Carrier> findByCarrierCode(String carrierCode);

    boolean existsByCarrierCode(String carrierCode);

    List<Carrier> findByStatus(String status);

    List<Carrier> findByLineName(String lineName);
}
