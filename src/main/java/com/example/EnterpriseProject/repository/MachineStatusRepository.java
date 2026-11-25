package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.MachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineStatusRepository extends JpaRepository<MachineStatus, Long> {

    Optional<MachineStatus> findByIpAddress(String ipAddress);

    List<MachineStatus> findByStatus(String status);

    List<MachineStatus> findByLineName(String lineName);
}

