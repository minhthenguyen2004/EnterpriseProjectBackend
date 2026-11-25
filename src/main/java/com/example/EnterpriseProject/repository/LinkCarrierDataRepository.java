package com.example.EnterpriseProject.repository;

import com.example.EnterpriseProject.entity.LinkCarrier;
import com.example.EnterpriseProject.entity.LinkCarrierData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkCarrierDataRepository extends JpaRepository<LinkCarrierData, Long> {

    List<LinkCarrierData> findByLinkCarrier_Id(Long linkCarrierId);

    List<LinkCarrierData> findByLinkCarrier(LinkCarrier linkCarrier);

    @Query("SELECT d FROM LinkCarrierData d WHERE d.linkCarrier.id = :idLinkCarrier")
    List<LinkCarrierData> findByIdLinkCarrier(@Param("idLinkCarrier") Long idLinkCarrier);

    List<LinkCarrierData> findByIdKey(Long idKey);
}
