package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Nhật ký bảo dưỡng & kiểm định khay gá định kỳ (PM)
 */
@Entity
@Table(
        name = "carrier_inspection_logs",
        indexes = {
                @Index(name = "idx_cil_carrier", columnList = "carrier_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierInspectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrier_code", length = 100, nullable = false)
    private String carrierCode;

    @Column(name = "inspection_type", length = 50)
    private String inspectionType;

    @Column(name = "inspector", length = 50)
    private String inspector;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "action_taken", length = 50)
    private String actionTaken;

    @Column(name = "cycle_count_at_inspection")
    private Integer cycleCountAtInspection;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "inspection_time")
    private Instant inspectionTime;
}

