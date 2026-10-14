package com.example.EnterpriseProject.entity;

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
 * Bảng aoi_data: Quản lý toàn bộ phiên kiểm tra ngoại quan quang học của trạm máy
 * Khớp với khối aoidata trong JSON payload
 */
@Entity
@Table(
    name = "aoi_data",
    indexes = {
        @Index(name = "idx_aoi_log_id", columnList = "log_id"),
        @Index(name = "idx_aoi_carrier", columnList = "carrier_code")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id", nullable = false, foreignKey = @ForeignKey(name = "fk_aoi_prod_logs"))
    private ProductionLog productionLog;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "cycle_time", precision = 8, scale = 2)
    private BigDecimal cycleTime;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "aoiData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AoiSummaryImage> summaryImages = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "aoiData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AoiFovInspection> fovInspections = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
