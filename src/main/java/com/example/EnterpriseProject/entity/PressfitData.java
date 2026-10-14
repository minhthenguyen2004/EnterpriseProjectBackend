package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.TestResult;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Bảng pressfit_data: Nhật ký thông số ép lực (Press-fit)
 */
@Entity
@Table(
    name = "pressfit_data",
    indexes = {
        @Index(name = "idx_press_log_id", columnList = "log_id")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PressfitData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false, foreignKey = @ForeignKey(name = "fk_press_prod_logs"))
    private ProductionLog productionLog;

    @Column(name = "press_position", length = 50, nullable = false)
    private String pressPosition;

    @Builder.Default
    @Column(length = 50, nullable = false)
    private String action = "PRESS";

    @Column(name = "press_force", precision = 10, scale = 2, nullable = false)
    private BigDecimal pressForce;

    @Column(name = "press_distance", precision = 10, scale = 3, nullable = false)
    private BigDecimal pressDistance;

    @Column(name = "press_time", precision = 6, scale = 2, nullable = false)
    private BigDecimal pressTime;

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
