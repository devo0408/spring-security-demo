package com.devo.product.web.mappers;

import com.devo.product.domain.ProductEntity;
import com.devo.product.domain.ProductInventoryEntity;
import com.devo.product.web.model.ProductViewDto;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.springframework.util.CollectionUtils.isEmpty;


public abstract class ProductMapperDecorator implements ProductMapper {

    private ProductMapper productMapper;

    @Autowired
    @Qualifier("delegate")
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ProductViewDto productToProductDto(ProductEntity productEntity) {
        ProductViewDto dto = productMapper.productToProductDto(productEntity);

        if(!isEmpty(productEntity.getProductInventoryEntity())) {
            val inventory = productEntity.getProductInventoryEntity()
                .stream()
                .map(ProductInventoryEntity::getQuantityOnHand)
                .reduce(0, Integer::sum);
            dto.setQuantityOnHand(inventory);
        }

        return dto;
    }
}
