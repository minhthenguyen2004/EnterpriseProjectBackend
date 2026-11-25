package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Quản lý hồ sơ và vòng đời khay gá mang hàng
 */
@Entity
@Table(
        name = "carriers",
        indexes = {
                @Index(name = "idx_carrier_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrier {

    @Id
    @Column(name = "carrier_code", length = 100, nullable = false)
    private String carrierCode;

    @Column(name = "carrier_type", length = 50)
    private String carrierType;

    @Builder.Default
    @Column(name = "total_cycle_count")
    private Integer totalCycleCount = 0;

    @Builder.Default
    @Column(name = "current_cycle_count")
    private Integer currentCycleCount = 0;

    @Column(name = "last_cycle_completed_time")
    private Instant lastCycleCompletedTime;

    @Column(name = "last_inspection_time")
    private Instant lastInspectionTime;

    @Column(name = "last_inspection_by", length = 50)
    private String lastInspectionBy;

    @Column(name = "last_inspection_result", length = 20)
    private String lastInspectionResult;

    @Builder.Default
    @Column(name = "status", length = 20)
    private String status = "ACTIVE";

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "product_name", length = 100)
    private String productName;
}
