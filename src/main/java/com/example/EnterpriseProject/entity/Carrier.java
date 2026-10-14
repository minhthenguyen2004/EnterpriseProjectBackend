package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.entity.base.BaseTimeEntity;
import com.example.EnterpriseProject.enums.CarrierStatus;
import jakarta.persistence.*;
import lombok.*;

/**
 * Bảng carriers: Quản lý danh mục và trạng thái thời gian thực của đồ gá tuần hoàn
 */
@Entity
@Table(
    name = "carriers",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_carrier_code", columnNames = {"carrier_code"})
    },
    indexes = {
        @Index(name = "idx_carrier_status", columnList = "status"),
        @Index(name = "idx_carrier_current_sn", columnList = "current_serial_number")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrier extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "carrier_code", length = 50, nullable = false, unique = true)
    private String carrierCode;

    @Column(name = "current_serial_number", length = 100)
    private String currentSerialNumber;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CarrierStatus status = CarrierStatus.EMPTY;

    @Builder.Default
    @Column(name = "total_cycles", nullable = false)
    private Integer totalCycles = 0;

    @Builder.Default
    @Column(name = "max_cycles", nullable = false)
    private Integer maxCycles = 5000;

    @Column(name = "last_station_code", length = 50)
    private String lastStationCode;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
