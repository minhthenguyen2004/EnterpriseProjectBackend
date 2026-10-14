package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.AoiCamera;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Bảng aoi_fov_inspections: Tầng chụp ảnh vùng FOV / Camera ngoại quan AOI
 */
@Entity
@Table(
    name = "aoi_fov_inspections",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_fov_aoi_cam_index", columnNames = {"aoi_data_id", "camera", "fov_index"})
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiFovInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aoi_data_id", nullable = false, foreignKey = @ForeignKey(name = "fk_fov_aoi_data"))
    private AoiData aoiData;

    @Column(name = "fov_index", nullable = false)
    private Integer fovIndex;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private AoiCamera camera;

    @Column(name = "fov_image_path", columnDefinition = "TEXT")
    private String fovImagePath;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "aoiFovInspection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<AoiComponentInspection> components = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
