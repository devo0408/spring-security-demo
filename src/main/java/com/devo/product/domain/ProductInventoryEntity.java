package com.devo.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductInventoryEntity extends BaseEntity {

    @ManyToOne
    private ProductEntity product;

    private Integer quantityOnHand = 0;

    @Builder
    public ProductInventoryEntity(
            Long id,
            Long version,
            Timestamp createdDate,
            Timestamp lastModifiedDate,
            ProductEntity productEntity,
            Integer quantityOnHand
    ) {
        super(id, version, createdDate, lastModifiedDate);
        this.product = productEntity;
        this.quantityOnHand = quantityOnHand;
    }

}
