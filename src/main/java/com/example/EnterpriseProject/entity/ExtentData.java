package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Dữ liệu file mở rộng / Datalog thô từ các máy chuyên dụng (Gluing AOI Datalog...)
 */
@Entity
@Table(
        name = "extent_datas",
        indexes = {
                @Index(name = "idx_ext_detail", columnList = "id_sn_detail")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_sn_detail")
    private Long idSnDetail;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "data_path", length = 1000)
    private String dataPath;

    @Column(name = "result", length = 20)
    private String result;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "server_name", length = 100)
    private String serverName;
}

