package com.devo.product.web.mappers;

import com.devo.product.domain.ProductOrderLineEntity;
import com.devo.product.exception.ProductNotFoundException;
import com.devo.product.repositories.ProductRepository;
import com.devo.product.web.model.ProductOrderLineDto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;


public abstract class ProductOrderLineMapperDecorator implements ProductOrderLineMapper {

    private ProductRepository productRepository;
    private ProductOrderLineMapper productOrderLineMapper;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    @Qualifier("delegate")
    public void setProductOrderLineMapper(ProductOrderLineMapper productOrderLineMapper) {
        this.productOrderLineMapper = productOrderLineMapper;
    }

    @Override
    public ProductOrderLineDto entityToDto(ProductOrderLineEntity entity) {
        ProductOrderLineDto orderLineDto = productOrderLineMapper.entityToDto(entity);
        orderLineDto.setProductId(entity.getProductEntity().getId());
        return orderLineDto;
    }

    @Override
    public ProductOrderLineEntity dtoToEntity(ProductOrderLineDto dto) {
        val product = Optional.of(dto)
                .map(ProductOrderLineDto::getProductId)
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(ProductNotFoundException::new);

        ProductOrderLineEntity orderLineEntity = productOrderLineMapper.dtoToEntity(dto);
        orderLineEntity.setProductEntity(product);
        return orderLineEntity;
    }
}
