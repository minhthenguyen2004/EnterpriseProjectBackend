package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Dữ liệu chi tiết EAV gắn liền với lượt gia công của SnDetail
 */
@Entity
@Table(
        name = "sn_details_datas",
        indexes = {
                @Index(name = "idx_snd_key", columnList = "id_key")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnDetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sn_detail", nullable = false, foreignKey = @ForeignKey(name = "fk_snd_sn"))
    private SnDetail snDetail;

    @Column(name = "id_key", nullable = false)
    private Long idKey;

    @Column(name = "value_max", length = 255)
    private String valueMax;

    @Column(name = "value", length = 255)
    private String value;
}

