package com.devo.product.web.model.write;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductOrderLineCreateDto {

    private UUID productId;

    private Integer orderQuantity = 0;

}
