package com.devo.product.web.mappers;

import com.devo.product.domain.ProductOrderLineEntity;
import com.devo.product.exception.ProductNotFoundException;
import com.devo.product.repositories.ProductRepository;
import com.devo.product.web.model.ProductOrderLineDto;
import com.devo.product.web.model.write.ProductOrderLineCreateDto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


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
        orderLineDto.setOrderQuantity(entity.getQuantityAllocated());
        return orderLineDto;
    }

    @Override
    public ProductOrderLineEntity dtoToEntity(ProductOrderLineCreateDto dto) {
        val product = productRepository.findById(dto.getProductId())
            .orElseThrow(ProductNotFoundException::new);

        return ProductOrderLineEntity.builder()
            .productEntity(product)
            .quantityAllocated(dto.getOrderQuantity())
            .build();
    }

}
