package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.CarrierSwapHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierSwapHistoryRepository extends JpaRepository<CarrierSwapHistory, Long> {

    List<CarrierSwapHistory> findBySerialNumberOrderBySwapTimeDesc(String serialNumber);

    List<CarrierSwapHistory> findByOldCarrierCode(String oldCarrierCode);

    List<CarrierSwapHistory> findByNewCarrierCode(String newCarrierCode);

    List<CarrierSwapHistory> findByIdSnDetail(Long idSnDetail);
}
