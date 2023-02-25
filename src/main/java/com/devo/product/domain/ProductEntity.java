package com.devo.product.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductEntity extends BaseEntity {

    @Builder
    public ProductEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         String title, ProductTypeEnum type, BigDecimal price, Set<ProductInventoryEntity> productInventoryEntity) {
        super(id, version, createdDate, lastModifiedDate);
        this.title = title;
        this.type = type;
        this.price = price;
        this.productInventoryEntity = productInventoryEntity;
    }

    private String title;

    @Column(nullable = false)
    private ProductTypeEnum type;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ProductInventoryEntity> productInventoryEntity = new HashSet<>();

}
