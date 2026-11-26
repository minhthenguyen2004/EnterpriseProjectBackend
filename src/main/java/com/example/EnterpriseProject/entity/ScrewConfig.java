package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.BooleanFlag;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Công thức & dung sai siết ốc tiêu chuẩn (Recipe)
 */
@Entity
@Table(name = "screw_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrewConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "position", length = 50)
    private String position;

    @Builder.Default
    @Column(name = "standard_volume", precision = 12, scale = 4)
    private BigDecimal standardVolume = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "standard_height", precision = 12, scale = 4)
    private BigDecimal standardHeight = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "standard_torque", precision = 12, scale = 4)
    private BigDecimal standardTorque = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "standard_angle", precision = 12, scale = 4)
    private BigDecimal standardAngle = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_volume", precision = 12, scale = 4)
    private BigDecimal limitVolume = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_height", precision = 12, scale = 4)
    private BigDecimal limitHeight = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_torque", precision = 12, scale = 4)
    private BigDecimal limitTorque = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_angle", precision = 12, scale = 4)
    private BigDecimal limitAngle = BigDecimal.ZERO;

    @Column(name = "unit_volume", length = 20)
    private String unitVolume;

    @Column(name = "unit_height", length = 20)
    private String unitHeight;

    @Column(name = "unit_torque", length = 20)
    private String unitTorque;

    @Column(name = "unit_angle", length = 20)
    private String unitAngle;

    @Builder.Default
    @Column(name = "is_require")
    private BooleanFlag isRequire = BooleanFlag.TRUE;

    @Builder.Default
    @Column(name = "is_active")
    private BooleanFlag isActive = BooleanFlag.TRUE;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}

