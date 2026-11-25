package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {

    List<ErrorLog> findBySerialNumberOrderByCreatedTimeDesc(String serialNumber);

    List<ErrorLog> findByErrorCodeOrderByCreatedTimeDesc(String errorCode);

    List<ErrorLog> findByLineNameAndStep(String lineName, Integer step);
}

