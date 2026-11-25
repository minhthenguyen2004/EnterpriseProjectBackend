package com.example.EnterpriseProject.entity;

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
    @Column(name = "allow_carrier_swap")
    private Boolean allowCarrierSwap = false;

    @Builder.Default
    @Column(name = "resolve_carrier_from_sn")
    private Boolean resolveCarrierFromSN = false;

    @Builder.Default
    @Column(name = "is_sfc_station")
    private Boolean isSfcStation = false;

    @Column(name = "main_key", length = 100)
    private String mainKey;

    @Builder.Default
    @Column(name = "allow_fail")
    private Boolean allowFail = false;

    @Column(name = "sfc_trigger_step")
    private Integer sfcTriggerStep;

    @Builder.Default
    @Column(name = "sfc_only_pass")
    private Boolean sfcOnlyPass = false;

    @Builder.Default
    @Column(name = "is_sfc_pre_check")
    private Boolean isSfcPreCheck = false;

    @Builder.Default
    @Column(name = "upload_data_mode")
    private Integer uploadDataMode = 0;
}

