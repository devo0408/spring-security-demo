package com.devo.product.web.model;

import com.devo.product.domain.ProductTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;

@Deprecated
@Getter
@Setter
@NoArgsConstructor
public class ProductWriteDto {

    @Builder
    public ProductWriteDto(String title, ProductTypeEnum productType, BigDecimal price) {

        this.title = title;
        this.type = productType;
        this.price = price;
    }

    private String title;

    private ProductTypeEnum type;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;

}
