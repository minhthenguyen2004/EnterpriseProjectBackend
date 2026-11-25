package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.SnDetail;
import com.example.EnterpriseProject.entity.SnDetailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnDetailDataRepository extends JpaRepository<SnDetailData, Long> {

    List<SnDetailData> findBySnDetail_Id(Long snDetailId);

    List<SnDetailData> findBySnDetail(SnDetail snDetail);

    @Query("SELECT d FROM SnDetailData d WHERE d.snDetail.id = :idSnDetail")
    List<SnDetailData> findByIdSnDetail(@Param("idSnDetail") Long idSnDetail);

    List<SnDetailData> findByIdKey(Long idKey);
}
