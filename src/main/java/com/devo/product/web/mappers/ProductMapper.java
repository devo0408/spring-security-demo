package com.devo.product.web.mappers;

import com.devo.product.domain.ProductEntity;
import com.devo.product.web.model.ProductViewDto;
import com.devo.product.web.model.ProductWriteDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = DateMapper.class)
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    ProductViewDto productToProductDto(ProductEntity productEntity);

    ProductEntity productWriteDtoToProduct(ProductWriteDto productWriteDto);

    ProductEntity updateProductFromProductWriteDto(ProductWriteDto productWriteDto, @MappingTarget ProductEntity productEntity);

}
