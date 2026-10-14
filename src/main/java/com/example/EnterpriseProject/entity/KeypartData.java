package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Bảng keypart_data: Danh sách linh kiện con gắn kết tại trạm (kps)
 */
@Entity
@Table(
    name = "keypart_data",
    indexes = {
        @Index(name = "idx_kp_log_id", columnList = "log_id"),
        @Index(name = "idx_kp_value", columnList = "kp_value")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeypartData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false, foreignKey = @ForeignKey(name = "fk_kp_prod_logs"))
    private ProductionLog productionLog;

    @Column(name = "kp_name", length = 100, nullable = false)
    private String kpName;

    @Column(name = "kp_value", length = 150, nullable = false)
    private String kpValue;

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
