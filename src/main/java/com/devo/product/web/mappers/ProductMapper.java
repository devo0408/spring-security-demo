package com.devo.product.web.mappers;

import com.devo.product.domain.ProductEntity;
import com.devo.product.web.model.ProductViewDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    ProductViewDto productToProductDto(ProductEntity productEntity);

}
