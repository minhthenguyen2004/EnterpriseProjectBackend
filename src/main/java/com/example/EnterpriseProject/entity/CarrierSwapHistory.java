package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Lịch sử tráo đổi / lật mặt khay gá của Serial Number
 */
@Entity
@Table(
        name = "carrier_swap_history",
        indexes = {
                @Index(name = "idx_csh_serial", columnList = "serial_number"),
                @Index(name = "idx_csh_time", columnList = "swap_time")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierSwapHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "step")
    private Integer step;

    @Column(name = "old_carrier_code", length = 100)
    private String oldCarrierCode;

    @Column(name = "new_carrier_code", length = 100)
    private String newCarrierCode;

    @Column(name = "swap_time")
    private Instant swapTime;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "id_sn_detail")
    private Long idSnDetail;
}

