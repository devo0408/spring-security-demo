package com.devo.product.web.model.write;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductOrderCreateDto {

    private List<ProductOrderLineCreateDto> productOrderLines;
}
