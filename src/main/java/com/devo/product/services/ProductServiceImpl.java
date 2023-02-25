package com.devo.product.services;

import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.exception.ProductNotFoundException;
import com.devo.product.repositories.ProductRepository;
import com.devo.product.web.mappers.ProductMapper;
import com.devo.product.web.model.ProductViewDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public Page<ProductViewDto> findAllOptionalParameters(@NonNull Pageable pageable, String productTitle, ProductTypeEnum productTypeEnum) {
        return productRepository.searchAllOptionalParameters(productTitle, productTypeEnum, pageable)
                .map(productMapper::productToProductDto);
    }

    @Override
    public ProductViewDto findProductByExternalUuidRequired(UUID externalUuid) {
        return productRepository.findByExternalUuid(externalUuid)
                .map(productMapper::productToProductDto)
                .orElseThrow(ProductNotFoundException::new);
    }

}
