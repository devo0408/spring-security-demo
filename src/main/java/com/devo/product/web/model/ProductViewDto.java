package com.devo.product.web.model;

import com.devo.product.domain.ProductTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductViewDto extends BaseViewDto {

    @Builder
    public ProductViewDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                          String title, ProductTypeEnum productType, BigDecimal price, Integer quantityOnHand) {

        super(id, version, createdDate, lastModifiedDate);
        this.title = title;
        this.type = productType;
        this.price = price;
        this.quantityOnHand = quantityOnHand;
    }

    private String title;

    private ProductTypeEnum type;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;

    private Integer quantityOnHand = 0;

}
