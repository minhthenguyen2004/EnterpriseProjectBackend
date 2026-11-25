package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Dữ liệu máy ép nén linh kiện / Shielding / Connector (21 cột đầy đủ)
 */
@Entity
@Table(
        name = "press_fit_datas",
        indexes = {
                @Index(name = "idx_press_detail", columnList = "id_sn_detail"),
                @Index(name = "idx_press_serial", columnList = "serial_number")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PressFitData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sn_detail")
    private Long idSnDetail;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "action", length = 50)
    private String action;

    @Column(name = "standard_force", precision = 12, scale = 4)
    private BigDecimal standardForce;

    @Column(name = "limit_force", precision = 12, scale = 4)
    private BigDecimal limitForce;

    @Column(name = "real_force", precision = 12, scale = 4)
    private BigDecimal realForce;

    @Column(name = "unit_force", length = 20)
    private String unitForce;

    @Column(name = "standard_time", precision = 12, scale = 4)
    private BigDecimal standardTime;

    @Column(name = "limit_time", precision = 12, scale = 4)
    private BigDecimal limitTime;

    @Column(name = "real_time", precision = 12, scale = 4)
    private BigDecimal realTime;

    @Column(name = "unit_time", length = 20)
    private String unitTime;

    @Column(name = "standard_distance", precision = 12, scale = 4)
    private BigDecimal standardDistance;

    @Column(name = "limit_distance", precision = 12, scale = 4)
    private BigDecimal limitDistance;

    @Column(name = "real_distance", precision = 12, scale = 4)
    private BigDecimal realDistance;

    @Column(name = "unit_distance", length = 20)
    private String unitDistance;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "created_time")
    private Instant createdTime;
}

