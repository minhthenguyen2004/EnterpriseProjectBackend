package com.example.EnterpriseProject.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Dữ liệu quét EAV đi kèm sự kiện liên kết khay gá
 */
@Entity
@Table(
        name = "link_carrier_datas",
        indexes = {
                @Index(name = "idx_lcd_key", columnList = "id_key")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkCarrierData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_link_carrier", nullable = false, foreignKey = @ForeignKey(name = "fk_lcd_lc"))
    private LinkCarrier linkCarrier;

    @Column(name = "id_key", nullable = false)
    private Long idKey;

    @Column(name = "value", length = 255)
    private String value;
}

