package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.entity.base.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Bảng operators: Quản lý danh mục công nhân & mật khẩu xác thực từ trạm
 */
@Entity
@Table(
    name = "operators",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_operator_code", columnNames = {"operator_code"})
    },
    indexes = {
        @Index(name = "idx_operator_active", columnList = "is_active")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operator extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operator_code", length = 50, nullable = false, unique = true)
    private String operatorCode;

    @JsonIgnore
    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "operator_name", length = 100, nullable = false)
    private String operatorName;

    @Column(length = 50)
    private String department;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
