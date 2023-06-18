package com.devo.product.web.model;

import com.devo.product.domain.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductOrderDto extends BaseViewDto {

    private UUID customerId;

    private List<ProductOrderLineDto> productOrderLines;

    private OrderStatusEnum orderStatus;

    @Builder
    public ProductOrderDto(Integer id,
                           Integer version,
                           OffsetDateTime createdDate,
                           OffsetDateTime lastModifiedDate,
                           UUID customerId,
                           List<ProductOrderLineDto> productOrderLines,
                           OrderStatusEnum orderStatus
    ) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.productOrderLines = productOrderLines;
        this.orderStatus = orderStatus;
    }
}
