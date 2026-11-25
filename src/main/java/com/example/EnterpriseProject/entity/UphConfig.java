package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Định mức sản lượng & chu kỳ chuẩn
 */
@Entity
@Table(name = "uph_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UphConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Builder.Default
    @Column(name = "target_uph")
    private Integer targetUph = 100;

    @Builder.Default
    @Column(name = "target_cycle_time", precision = 8, scale = 2)
    private BigDecimal targetCycleTime = new BigDecimal("36.00");

    @Builder.Default
    @Column(name = "warning_threshold")
    private Integer warningThreshold = 0;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;
}

