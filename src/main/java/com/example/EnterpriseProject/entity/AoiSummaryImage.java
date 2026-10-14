package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.AoiCamera;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Bảng aoi_summary_images: Lưu trữ ảnh chụp tổng thể toàn bo mạch từ các hướng camera (TOP, BOT)
 * Khớp với mảng imagesummarys trong aoidata
 */
@Entity
@Table(
    name = "aoi_summary_images",
    indexes = {
        @Index(name = "idx_summary_aoi_id", columnList = "aoi_data_id")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiSummaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aoi_data_id", nullable = false, foreignKey = @ForeignKey(name = "fk_summary_aoi_data"))
    private AoiData aoiData;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private AoiCamera camera;

    @Column(name = "image_path", columnDefinition = "TEXT", nullable = false)
    private String imagePath;

    @Column(length = 150)
    private String description;

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
