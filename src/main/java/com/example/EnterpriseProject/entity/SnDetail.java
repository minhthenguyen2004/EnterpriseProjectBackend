package com.example.EnterpriseProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Giao dịch sản xuất chính ghi nhận lượt phôi gia công qua mỗi trạm máy
 */
@Entity
@Table(
        name = "sn_details",
        indexes = {
                @Index(name = "idx_sn_serial", columnList = "serial_number"),
                @Index(name = "idx_sn_carrier", columnList = "carrier_code"),
                @Index(name = "idx_sn_instation", columnList = "in_station_time")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "factory", length = 10)
    private String factory;

    @Column(name = "floor", length = 10)
    private String floor;

    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Column(name = "machine_name", length = 200)
    private String machineName;

    @Column(name = "product_name", length = 200)
    private String productName;

    @Column(name = "product_type", length = 100)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "group_name", length = 200)
    private String groupName;

    @Column(name = "model_name", length = 200)
    private String modelName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "work_date", length = 20)
    private String workDate;

    @Column(name = "work_section")
    private Integer workSection;

    @Column(name = "serial_number", length = 200)
    private String serialNumber;

    @Column(name = "carrier_code", length = 200)
    private String carrierCode;

    @Builder.Default
    @Column(name = "good_qty")
    private Integer goodQty = 0;

    @Builder.Default
    @Column(name = "pass_qty")
    private Integer passQty = 0;

    @Builder.Default
    @Column(name = "fail_qty")
    private Integer failQty = 0;

    @Column(name = "station_sequence")
    private Integer stationSequence;

    @Column(name = "in_station_time")
    private Instant inStationTime;

    @Column(name = "cycle_time", precision = 10, scale = 3)
    private BigDecimal cycleTime;

    @Column(name = "employee_id", length = 100)
    private String employeeId;

    @Column(name = "error_code", length = 1000)
    private String errorCode;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "mo", length = 200)
    private String mo;

    @JsonIgnore
    @OneToMany(mappedBy = "snDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<SnDetailData> detailDatas = new ArrayList<>();
}

