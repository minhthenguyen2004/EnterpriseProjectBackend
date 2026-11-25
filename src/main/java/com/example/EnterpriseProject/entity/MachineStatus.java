package com.example.EnterpriseProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Trạng thái thời gian thực tức thời của từng trạm máy (Snapshot)
 */
@Entity
@Table(
        name = "machine_status",
        indexes = {
                @Index(name = "idx_ms_status", columnList = "status"),
                @Index(name = "idx_ms_ip", columnList = "ip_address")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 55)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "step")
    private Integer step;

    @Builder.Default
    @Column(name = "status", length = 20)
    private String status = "RUN";

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "machineStatus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MachineStatusHistory> statusHistories = new ArrayList<>();
}

