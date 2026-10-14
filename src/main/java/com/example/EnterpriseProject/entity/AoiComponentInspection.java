package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.TestResult;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Bảng aoi_component_inspections: Tầng chi tiết linh kiện kiểm tra AOI
 */
@Entity
@Table(
    name = "aoi_component_inspections",
    indexes = {
        @Index(name = "idx_aoi_comp_fov", columnList = "aoi_fov_id")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiComponentInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aoi_fov_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comp_fov"))
    private AoiFovInspection aoiFovInspection;

    @Column(name = "component_name", length = 50, nullable = false)
    private String componentName;

    @Column(length = 50, nullable = false)
    private String location;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal score;

    @Column(precision = 10, scale = 4, nullable = false)
    private BigDecimal measure;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TestResult result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "crop_image_path", columnDefinition = "TEXT")
    private String cropImagePath;

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
