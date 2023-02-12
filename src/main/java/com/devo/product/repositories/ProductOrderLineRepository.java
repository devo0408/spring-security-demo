package com.devo.product.repositories;

import com.devo.product.domain.ProductOrderLineEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProductOrderLineRepository extends PagingAndSortingRepository<ProductOrderLineEntity, UUID> {
}
