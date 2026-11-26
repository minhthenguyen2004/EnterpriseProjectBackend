package com.example.EnterpriseProject.entity;

import com.example.EnterpriseProject.enums.BooleanFlag;
import jakarta.persistence.*;
import lombok.*;

/**
 * Cấu hình ma trận các khóa kiểm tra động (Dynamic Validation Keys)
 */


@Entity
@Table(
        name = "product_configs",
        indexes = {
                @Index(name = "idx_prod_cfg_lookup", columnList = "product_name, product_type, step, group_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "step", nullable = false)
    private Integer step;

    @Column(name = "group_name", length = 100, nullable = false)
    private String groupName;

    @Column(name = "key_name", length = 100)
    private String keyName;

    @Column(name = "display_name", length = 100)
    private String displayName;

    @Builder.Default
    @Column(name = "value_type", length = 50)
    private String valueType = "string";

    @Builder.Default
    @Column(name = "is_active")
    private BooleanFlag isActive = BooleanFlag.TRUE;

    @Builder.Default
    @Column(name = "is_require")
    private BooleanFlag isRequire = BooleanFlag.TRUE;

    @Builder.Default
    @Column(name = "is_verify")
    private BooleanFlag isVerify = BooleanFlag.FALSE;

    @Builder.Default
    @Column(name = "is_by_pass")
    private BooleanFlag isByPass = BooleanFlag.FALSE;

    @Builder.Default
    @Column(name = "is_sfc")
    private BooleanFlag isSfc = BooleanFlag.FALSE;
}
