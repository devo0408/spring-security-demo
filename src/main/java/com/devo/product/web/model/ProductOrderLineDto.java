package com.devo.product.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductOrderLineDto extends BaseViewDto {

    private Long productId;

    private Integer orderQuantity = 0;

    @Builder
    public ProductOrderLineDto(Long id,
                               Integer version,
                               OffsetDateTime createdDate,
                               OffsetDateTime lastModifiedDate,
                               Long productId,
                               Integer orderQuantity
    ) {
        super(id, version, createdDate, lastModifiedDate);
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
}
