package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Dữ liệu siết ốc tự động từ súng siết điện tử (27 cột đầy đủ)
 */
@Entity
@Table(
        name = "screw_datas",
        indexes = {
                @Index(name = "idx_screw_detail", columnList = "id_sn_detail"),
                @Index(name = "idx_screw_serial", columnList = "serial_number")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrewData {

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

    @Column(name = "standard_volume", precision = 12, scale = 4)
    private BigDecimal standardVolume;

    @Column(name = "limit_volume", precision = 12, scale = 4)
    private BigDecimal limitVolume;

    @Column(name = "standard_height", precision = 12, scale = 4)
    private BigDecimal standardHeight;

    @Column(name = "limit_height", precision = 12, scale = 4)
    private BigDecimal limitHeight;

    @Column(name = "real_volume", precision = 12, scale = 4)
    private BigDecimal realVolume;

    @Column(name = "real_height", precision = 12, scale = 4)
    private BigDecimal realHeight;

    @Column(name = "torque", precision = 12, scale = 4)
    private BigDecimal torque;

    @Column(name = "angle", precision = 12, scale = 4)
    private BigDecimal angle;

    @Column(name = "time", precision = 12, scale = 4)
    private BigDecimal time;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "unit", length = 20)
    private String unit;

    @Column(name = "standard_torque", precision = 12, scale = 4)
    private BigDecimal standardTorque;

    @Column(name = "limit_torque", precision = 12, scale = 4)
    private BigDecimal limitTorque;

    @Column(name = "standard_angle", precision = 12, scale = 4)
    private BigDecimal standardAngle;

    @Column(name = "limit_angle", precision = 12, scale = 4)
    private BigDecimal limitAngle;

    @Column(name = "unit_volume", length = 20)
    private String unitVolume;

    @Column(name = "unit_height", length = 20)
    private String unitHeight;

    @Column(name = "unit_torque", length = 20)
    private String unitTorque;

    @Column(name = "unit_angle", length = 20)
    private String unitAngle;
}
