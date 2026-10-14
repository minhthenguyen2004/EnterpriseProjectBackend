package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.entity.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Bảng machines: Cấu hình và danh mục trạm máy tự động hóa
 */
@Entity
@Table(
    name = "machines",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_machine_line_station", columnNames = {"line_id", "station_code"}),
        @UniqueConstraint(name = "uk_machine_line_sequence", columnNames = {"line_id", "sequence_order"})
    },
    indexes = {
        @Index(name = "idx_machine_station_ip", columnList = "station_ip")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Machine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", nullable = false, foreignKey = @ForeignKey(name = "fk_machines_lines"))
    private Line line;

    @Column(name = "group_name", length = 100, nullable = false)
    private String groupName;

    @Column(name = "station_code", length = 50, nullable = false)
    private String stationCode;

    @Column(name = "machine_name", length = 100, nullable = false)
    private String machineName;

    @Column(name = "station_ip", length = 45, nullable = false)
    private String stationIp;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @Builder.Default
    @Column(name = "target_uph", nullable = false)
    private Integer targetUph = 100;

    @Builder.Default
    @Column(name = "target_cycle_time", precision = 6, scale = 2, nullable = false)
    private BigDecimal targetCycleTime = new BigDecimal("36.00");

    @Builder.Default
    @Column(name = "target_upd", nullable = false)
    private Integer targetUpd = 0;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MachineStatusLog> statusLogs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductionLog> productionLogs = new ArrayList<>();
}
