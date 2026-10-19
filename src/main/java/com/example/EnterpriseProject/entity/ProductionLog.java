package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.DataType;
import com.example.EnterpriseProject.enums.TestResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Bảng production_logs: Giao dịch sản xuất trung tâm từ trạm máy
 */
@Entity
@Table(
    name = "production_logs",
    indexes = {
        @Index(name = "idx_prod_machine_created", columnList = "machine_id, created_at"),
        @Index(name = "idx_prod_sn_created", columnList = "serial_number, created_at"),
        @Index(name = "idx_prod_carrier", columnList = "carrier_code"),
        @Index(name = "idx_prod_mo_result", columnList = "mo_id, result")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Machine machine;

    @Column(name = "station_code", length = 50)
    private String stationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mo_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ManufacturingOrder manufacturingOrder;

    // @Column(name = "mo_number", length = 100, nullable = false)
    // private String moNumber;

    @Column(name = "carrier_code", length = 100, nullable = false)
    private String carrierCode;

    @Column(name = "serial_number", length = 100, nullable = false)
    private String serialNumber;

    @Column(length = 50, nullable = false)
    private String step;

    @Builder.Default
    @Column(name = "run_sequence", nullable = false)
    private Integer runSequence = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", length = 20, nullable = false)
    private DataType dataType;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TestResult result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Builder.Default
    @Column(name = "cycle_time", precision = 8, scale = 2, nullable = false)
    private BigDecimal cycleTime = BigDecimal.ZERO;

    @Column(name = "operator_user", length = 50)
    private String operatorUser;

    @Builder.Default
    @Column(name = "is_void", nullable = false)
    private Boolean isVoid = false;

    @Column(name = "voided_at")
    private Instant voidedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voided_by_log_id", foreignKey = @ForeignKey(name = "fk_prod_voided_by"))
    private ProductionLog voidedByLog;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "productionLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<KeypartData> keyparts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productionLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ScrewData> screwDataList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productionLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PressfitData> pressfitDataList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productionLog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AoiData> aoiDataList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
