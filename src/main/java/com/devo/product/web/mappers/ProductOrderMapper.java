package com.devo.product.web.mappers;

import com.devo.product.domain.ProductOrderEntity;
import com.devo.product.web.model.ProductOrderDto;
import com.devo.product.web.model.write.ProductOrderCreateDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, ProductOrderLineMapper.class})
public interface ProductOrderMapper {

    ProductOrderDto entityToDto(ProductOrderEntity entity);

    ProductOrderEntity dtoToEntity(ProductOrderCreateDto dto);
}
