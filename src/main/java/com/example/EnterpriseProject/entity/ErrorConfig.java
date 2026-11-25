package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Từ điển mã lỗi chuẩn của hệ thống MES
 */
@Entity
@Table(name = "error_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorConfig {

    @Id
    @Column(name = "error_key")
    private Integer errorKey;

    @Column(name = "code_value", nullable = false)
    private Integer codeValue;

    @Column(name = "description", length = 500)
    private String description;
}

