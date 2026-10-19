package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.MachineState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Bảng machine_status_logs: Nhật ký trạng thái máy (RUN, IDLE, STOP, ERROR)
 * Phục vụ vẽ biểu đồ Machine Runtime Timeline
 */
@Entity
@Table(
    name = "machine_status_logs",
    indexes = {
        @Index(name = "idx_msl_machine_start", columnList = "machine_id, start_time")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineStatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Machine machine;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private MachineState status;

    @Column(name = "building")
    private String building;

    @Column(name = "floor")
    private String floor;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (startTime == null) {
            startTime = Instant.now();
        }
    }
}
