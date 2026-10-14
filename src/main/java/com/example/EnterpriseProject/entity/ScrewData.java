package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.TestResult;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Bảng screw_data: Nhật ký đo lường siết ốc tự động
 */
@Entity
@Table(
    name = "screw_data",
    indexes = {
        @Index(name = "idx_screw_log_id", columnList = "log_id")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrewData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false, foreignKey = @ForeignKey(name = "fk_screw_prod_logs"))
    private ProductionLog productionLog;

    @Column(name = "screw_position", length = 50, nullable = false)
    private String screwPosition;

    @Builder.Default
    @Column(length = 50, nullable = false)
    private String action = "screw";

    @Column(precision = 8, scale = 3, nullable = false)
    private BigDecimal torque;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal angle;

    @Column(name = "screw_time", precision = 6, scale = 2, nullable = false)
    private BigDecimal screwTime;

    @Column(name = "standard_volume", precision = 10, scale = 3)
    private BigDecimal standardVolume;

    @Column(name = "limit_volume", precision = 10, scale = 3)
    private BigDecimal limitVolume;

    @Column(name = "real_volume", precision = 10, scale = 3)
    private BigDecimal realVolume;

    @Column(name = "standard_height", precision = 10, scale = 3)
    private BigDecimal standardHeight;

    @Column(name = "limit_height", precision = 10, scale = 3)
    private BigDecimal limitHeight;

    @Column(name = "real_height", precision = 10, scale = 3)
    private BigDecimal realHeight;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TestResult result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
