package com.devo.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductInventoryEntity extends BaseEntity {

    @Builder
    public ProductInventoryEntity(UUID id, UUID externalUuid, Long version, Timestamp createdDate, Timestamp lastModifiedDate, ProductEntity productEntity, Integer quantityOnHand) {
        super(id, externalUuid, version, createdDate, lastModifiedDate);
        this.product = productEntity;
        this.quantityOnHand = quantityOnHand;
    }

    @ManyToOne
    private ProductEntity product;

    private Integer quantityOnHand = 0;

}
