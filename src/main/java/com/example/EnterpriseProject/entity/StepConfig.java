package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.BooleanFlag;
import jakarta.persistence.*;
import lombok.*;

/**
 * Cấu hình quy tắc luồng công đoạn (Routing rules per Step)
 */
@Entity
@Table(
        name = "step_configs",
        indexes = {
                @Index(name = "idx_step_cfg_lookup", columnList = "product_name, product_type, line_name, step")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100, nullable = false)
    private String lineName;

    @Column(name = "step", nullable = false)
    private Integer step;

    @Builder.Default
    @Column(name = "twin_step")
    private Integer twinStep = 0;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "allow_carrier_swap", length = 10)
    private BooleanFlag allowCarrierSwap = BooleanFlag.FALSE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "resolve_carrier_from_sn", length = 10)
    private BooleanFlag resolveCarrierFromSN = BooleanFlag.FALSE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "is_sfc_station", length = 10)
    private BooleanFlag isSfcStation = BooleanFlag.FALSE;

    @Column(name = "main_key", length = 100)
    private String mainKey;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "allow_fail", length = 10)
    private BooleanFlag allowFail = BooleanFlag.FALSE;

    @Column(name = "sfc_trigger_step")
    private Integer sfcTriggerStep;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "sfc_only_pass", length = 10)
    private BooleanFlag sfcOnlyPass = BooleanFlag.FALSE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "is_sfc_pre_check", length = 10)
    private BooleanFlag isSfcPreCheck = BooleanFlag.FALSE;

    @Builder.Default
    @Column(name = "upload_data_mode")
    private Integer uploadDataMode = 0;
}

