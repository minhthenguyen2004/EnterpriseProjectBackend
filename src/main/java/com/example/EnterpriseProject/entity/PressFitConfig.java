package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Công thức & dung sai ép nén tiêu chuẩn (Recipe)
 */
@Entity
@Table(name = "press_fit_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PressFitConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "position", length = 50)
    private String position;

    @Builder.Default
    @Column(name = "standard_force", precision = 12, scale = 4)
    private BigDecimal standardForce = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_force", precision = 12, scale = 4)
    private BigDecimal limitForce = BigDecimal.ZERO;

    @Column(name = "unit_force", length = 20)
    private String unitForce;

    @Builder.Default
    @Column(name = "standard_time", precision = 12, scale = 4)
    private BigDecimal standardTime = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_time", precision = 12, scale = 4)
    private BigDecimal limitTime = BigDecimal.ZERO;

    @Column(name = "unit_time", length = 20)
    private String unitTime;

    @Builder.Default
    @Column(name = "standard_distance", precision = 12, scale = 4)
    private BigDecimal standardDistance = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "limit_distance", precision = 12, scale = 4)
    private BigDecimal limitDistance = BigDecimal.ZERO;

    @Column(name = "unit_distance", length = 20)
    private String unitDistance;

    @Builder.Default
    @Column(name = "is_require")
    private Boolean isRequire = true;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "updated_time")
    private Instant updatedTime;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}

