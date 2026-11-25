package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.MachineStatus;
import com.example.EnterpriseProject.entity.MachineStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineStatusHistoryRepository extends JpaRepository<MachineStatusHistory, Long> {

    List<MachineStatusHistory> findByMachineStatus_IdOrderByUpdateDateDesc(Long machineStatusId);

    List<MachineStatusHistory> findByMachineStatusOrderByUpdateDateDesc(MachineStatus machineStatus);

    @Query("SELECT h FROM MachineStatusHistory h WHERE h.machineStatus.id = :idMachineStatus ORDER BY h.updateDate DESC")
    List<MachineStatusHistory> findByIdMachineStatusOrderByUpdateDateDesc(@Param("idMachineStatus") Long idMachineStatus);
}
