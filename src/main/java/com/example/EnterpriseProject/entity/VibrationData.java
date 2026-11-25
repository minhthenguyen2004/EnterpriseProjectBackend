package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Dữ liệu kiểm tra độ rung và động cơ tự động
 */
@Entity
@Table(
        name = "vibration_data",
        indexes = {
                @Index(name = "idx_vib_detail", columnList = "id_sn_detail")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VibrationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sn_detail")
    private Long idSnDetail;

    @Column(name = "machine_type", length = 50)
    private String machineType;

    @Column(name = "controller", length = 100)
    private String controller;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "action", length = 50)
    private String action;

    @Column(name = "voltage", precision = 12, scale = 4)
    private BigDecimal voltage;

    @Column(name = "pressure", precision = 12, scale = 4)
    private BigDecimal pressure;

    @Column(name = "time", precision = 12, scale = 4)
    private BigDecimal time;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "unit", length = 20)
    private String unit;
}

