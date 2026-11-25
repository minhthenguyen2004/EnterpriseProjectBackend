package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

    List<ActionLog> findByEmployeeIdOrderByCreatedAtDesc(String employeeId);

    List<ActionLog> findByTableNameOrderByCreatedAtDesc(String tableName);
}

