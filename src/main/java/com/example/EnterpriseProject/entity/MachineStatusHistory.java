package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Lịch sử chuyển đổi trạng thái máy
 */
@Entity
@Table(name = "machine_status_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_machine_status", nullable = false, foreignKey = @ForeignKey(name = "fk_msh_ms"))
    private MachineStatus machineStatus;

    @Column(name = "current_status", length = 20)
    private String currentStatus;

    @Column(name = "new_status", length = 20)
    private String newStatus;

    @Column(name = "update_date")
    private Instant updateDate;
}

