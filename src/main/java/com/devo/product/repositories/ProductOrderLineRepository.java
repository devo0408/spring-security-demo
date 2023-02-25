package com.devo.product.repositories;

import com.devo.product.domain.ProductOrderLineEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

@Deprecated
public interface ProductOrderLineRepository extends PagingAndSortingRepository<ProductOrderLineEntity, UUID> {
}
