package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Cấu hình định mức vòng đời và bảo dưỡng khay gá
 */
@Entity
@Table(name = "carrier_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "carrier_type", length = 50)
    private String carrierType;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Builder.Default
    @Column(name = "end_step")
    private Integer endStep = 0;

    @Builder.Default
    @Column(name = "max_cycle_count")
    private Integer maxCycleCount = 5000;

    @Builder.Default
    @Column(name = "warning_cycle_count")
    private Integer warningCycleCount = 4500;

    @Builder.Default
    @Column(name = "max_interval_hours")
    private Integer maxIntervalHours = 0;

    @Column(name = "fixed_schedule_times", length = 255)
    private String fixedScheduleTimes;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_time")
    private Instant updatedTime;
}

