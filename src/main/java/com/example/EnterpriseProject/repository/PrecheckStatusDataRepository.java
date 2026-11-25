package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.PrecheckStatus;
import com.example.EnterpriseProject.entity.PrecheckStatusData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecheckStatusDataRepository extends JpaRepository<PrecheckStatusData, Long> {

    List<PrecheckStatusData> findByPrecheckStatus_Id(Long precheckId);

    List<PrecheckStatusData> findByPrecheckStatus(PrecheckStatus precheckStatus);

    @Query("SELECT d FROM PrecheckStatusData d WHERE d.precheckStatus.id = :idPrecheck")
    List<PrecheckStatusData> findByIdPrecheck(@Param("idPrecheck") Long idPrecheck);

    List<PrecheckStatusData> findByIdKey(Long idKey);
}
