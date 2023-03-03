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
public class ProductOrderLineEntity extends BaseEntity {

    @ManyToOne
    private ProductEntity productEntity;

    private Integer quantityAllocated = 0;

    @Builder
    public ProductOrderLineEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, ProductEntity productEntity, Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        //this.productOrderEntity = productOrderEntity;
        this.productEntity = productEntity;
        this.quantityAllocated = quantityAllocated;
    }

}
