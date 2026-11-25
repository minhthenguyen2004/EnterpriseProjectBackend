package com.example.EnterpriseProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Ghi nhận sự kiện liên kết khay gá phôi (Carrier) với Serial Number và Lệnh MO
 */
@Entity
@Table(
        name = "link_carriers",
        indexes = {
                @Index(name = "idx_lc_carrier", columnList = "carrier_code"),
                @Index(name = "idx_lc_serial", columnList = "serial_number")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkCarrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "model_name", length = 100)
    private String modelName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "carrier_code", length = 100)
    private String carrierCode;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "in_station_time")
    private Instant inStationTime;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "mo", length = 100)
    private String mo;

    @JsonIgnore
    @OneToMany(mappedBy = "linkCarrier", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<LinkCarrierData> linkCarrierDatas = new ArrayList<>();
}

