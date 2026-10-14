package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.entity.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Bảng lines: Danh mục chuyền sản xuất tự động hóa
 */
@Entity
@Table(
    name = "lines",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_lines_line_code", columnNames = {"line_code"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Line extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "line_code", length = 50, nullable = false, unique = true)
    private String lineCode;

    @Column(name = "line_name", length = 100, nullable = false)
    private String lineName;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Machine> machines = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ManufacturingOrder> manufacturingOrders = new ArrayList<>();
}
