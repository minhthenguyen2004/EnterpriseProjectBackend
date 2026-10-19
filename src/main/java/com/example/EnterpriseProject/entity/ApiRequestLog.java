package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Bảng api_request_logs: Nhật ký giao tiếp API thô (Audit Trail & Performance Latency)
 */
@Entity
@Table(
    name = "api_request_logs",
    indexes = {
        @Index(name = "idx_api_ip_created", columnList = "station_ip, created_at"),
        @Index(name = "idx_api_created", columnList = "created_at")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String endpoint;

    @Column(name = "http_method", length = 10, nullable = false)
    private String httpMethod;

    @Column(name = "station_ip", length = 45)
    private String stationIp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Machine machine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductionLog productionLog;

    @Column(name = "request_payload", columnDefinition = "json")
    private String requestPayload;

    @Column(name = "response_payload", columnDefinition = "json")
    private String responsePayload;

    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Column(name = "latency_ms")
    private Integer latencyMs;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

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
