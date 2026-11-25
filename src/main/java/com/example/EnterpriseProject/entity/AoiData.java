package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Dữ liệu kiểm tra ngoại quan quang học tự động AOI
 */
@Entity
@Table(
        name = "aoi_datas",
        indexes = {
                @Index(name = "idx_aoi_sn", columnList = "serial_number"),
                @Index(name = "idx_aoi_detail", columnList = "id_sn_detail")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AoiData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sn_detail")
    private Long idSnDetail;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "camera", length = 50)
    private String camera;

    @Column(name = "fov", length = 50)
    private String fov;

    @Column(name = "component_name", length = 100)
    private String componentName;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "score", precision = 8, scale = 4)
    private BigDecimal score;

    @Column(name = "measure", precision = 12, scale = 4)
    private BigDecimal measure;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "image_path", length = 500)
    private String imagePath;

    @Column(name = "crop_image_path", length = 500)
    private String cropImagePath;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "server_name", length = 100)
    private String serverName;
}
