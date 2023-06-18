package com.devo.product.web.mappers;

import com.devo.product.domain.ProductOrderLineEntity;
import com.devo.product.web.model.ProductOrderLineDto;
import com.devo.product.web.model.write.ProductOrderLineCreateDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(ProductOrderLineMapperDecorator.class)
public interface ProductOrderLineMapper {

    ProductOrderLineDto entityToDto(ProductOrderLineEntity entity);

    ProductOrderLineEntity dtoToEntity(ProductOrderLineCreateDto dto);
}
