package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Nhật ký kiểm toán thao tác người dùng (User Action Audit Trail)
 */
@Entity
@Table(
        name = "action_logs",
        indexes = {
                @Index(name = "idx_act_user", columnList = "employee_id"),
                @Index(name = "idx_act_created", columnList = "created_at")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", length = 50)
    private String employeeId;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "action_type", length = 50)
    private String actionType;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "table_name", length = 100)
    private String tableName;

    @Column(name = "record_id", length = 100)
    private String recordId;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "line_name", length = 100)
    private String lineName;

    @Column(name = "step")
    private Integer step;

    @Column(name = "machine_name", length = 100)
    private String machineName;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "old_values", columnDefinition = "LONGTEXT")
    private String oldValues;

    @Column(name = "new_values", columnDefinition = "LONGTEXT")
    private String newValues;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "client_ip", length = 45)
    private String clientIp;

    @Column(name = "user_agent", length = 255)
    private String userAgent;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "created_at")
    private Instant createdAt;
}

