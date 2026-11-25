package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Nhật ký lỗi hệ thống và lỗi trạm máy phát sinh trong quá trình sản xuất
 */
@Entity
@Table(
        name = "error_logs",
        indexes = {
                @Index(name = "idx_err_serial", columnList = "serial_number"),
                @Index(name = "idx_err_code", columnList = "error_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error_source", length = 50)
    private String errorSource;

    @Column(name = "phase", length = 50)
    private String phase;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "station_sequence")
    private Integer stationSequence;

    @Column(name = "error_code", length = 50)
    private String errorCode;

    @Column(name = "log_message", columnDefinition = "TEXT")
    private String logMessage;

    @Column(name = "created_time")
    private Instant createdTime;
}

