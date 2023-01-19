package com.devo.product.services;

import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.exception.ProductCreationException;
import com.devo.product.exception.ProductNotFoundException;
import com.devo.product.repositories.ProductRepository;
import com.devo.product.web.mappers.ProductMapper;
import com.devo.product.web.model.ProductViewDto;
import com.devo.product.web.model.ProductWriteDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    @Override
    public ProductViewDto createProduct(ProductWriteDto productWriteDto) {
        return Optional.of(productWriteDto)
                .map(productMapper::productWriteDtoToProduct)
                .map(productRepository::save)
                .map(productMapper::productToProductDto)
                .orElseThrow(ProductCreationException::new);
    }

    @Override
    public ProductViewDto updateProduct(UUID externalUuid, ProductWriteDto productWriteDto) {
        return productRepository.findByExternalUuid(externalUuid)
                .map(entity -> productMapper.updateProductFromProductWriteDto(productWriteDto, entity))
                .map(productRepository::save)
                .map(productMapper::productToProductDto)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteByUuid(UUID externalUuid) {
        // todo: @Denys implement Soft delete
        throw new UnsupportedOperationException();
    }
}
