package com.example.EnterpriseProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Phiên xác thực sơ bộ PreCheck trước khi máy hoạt động
 */
@Entity
@Table(
        name = "precheck_status",
        indexes = {
                @Index(name = "idx_precheck_carrier", columnList = "carrier_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecheckStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Builder.Default
    @Column(name = "is_pre_check")
    private Boolean isPreCheck = false;

    @Column(name = "pre_check_date")
    private Instant preCheckDate;

    @JsonIgnore
    @OneToMany(mappedBy = "precheckStatus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PrecheckStatusData> precheckDatas = new ArrayList<>();
}

