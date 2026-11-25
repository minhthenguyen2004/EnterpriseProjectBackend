package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Dữ liệu quét EAV trong phiên PreCheck
 */
@Entity
@Table(
        name = "precheck_status_datas",
        indexes = {
                @Index(name = "idx_pre_key", columnList = "id_key")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrecheckStatusData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_precheck", nullable = false, foreignKey = @ForeignKey(name = "fk_pre_check"))
    private PrecheckStatus precheckStatus;

    @Column(name = "id_key", nullable = false)
    private Long idKey;

    @Column(name = "value", length = 255)
    private String value;
}

