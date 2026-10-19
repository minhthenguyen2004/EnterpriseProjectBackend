package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.SessionStatus;
import com.example.EnterpriseProject.enums.ShiftType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Bảng machine_sessions: Phiên phân công công nhân trực trạm máy theo ca
 */
@Entity
@Table(
    name = "machine_sessions",
    indexes = {
        @Index(name = "idx_session_lookup", columnList = "machine_id, status"),
        @Index(name = "idx_session_operator", columnList = "operator_code, work_date, shift")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Machine machine;

    @Column(name = "operator_code", length = 50, nullable = false)
    private String operatorCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private ShiftType shift;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @Column(name = "logout_time")
    private Instant logoutTime;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private SessionStatus status = SessionStatus.ACTIVE;

    @Column(length = 255)
    private String note;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (loginTime == null) {
            loginTime = Instant.now();
        }
        if (workDate == null) {
            workDate = LocalDate.now();
        }
    }
}
