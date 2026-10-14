package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.entity.base.BaseTimeEntity;
import com.example.EnterpriseProject.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Bảng manufacturing_orders: Danh mục công lệnh sản xuất
 */
@Entity
@Table(
    name = "manufacturing_orders",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_mo_number", columnNames = {"mo_number"})
    },
    indexes = {
        @Index(name = "idx_mo_line_status", columnList = "line_id, status"),
        @Index(name = "idx_mo_part_number", columnList = "part_number")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturingOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mo_number", length = 100, nullable = false, unique = true)
    private String moNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", nullable = false, foreignKey = @ForeignKey(name = "fk_mo_lines"))
    private Line line;

    @Column(name = "part_number", length = 50, nullable = false)
    private String partNumber;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_type", length = 50, nullable = false)
    private String productType;

    @Column(name = "target_qty", nullable = false)
    private Integer targetQty;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private OrderStatus status = OrderStatus.OPEN;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturingOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProductionLog> productionLogs = new ArrayList<>();
}
