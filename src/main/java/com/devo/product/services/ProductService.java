package com.devo.product.services;

import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.web.model.ProductViewDto;
import com.devo.product.web.model.ProductWriteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Page<ProductViewDto> findAllOptionalParameters(Pageable pageable, String productTitle, ProductTypeEnum productTypeEnum);

    ProductViewDto findProductByExternalUuidRequired(UUID uuid);

    ProductViewDto createProduct(ProductWriteDto productWriteDto);

    ProductViewDto updateProduct(UUID productId, ProductWriteDto productWriteDto);

    void deleteByUuid(UUID productId);


}
