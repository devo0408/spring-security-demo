package com.devo.product.repositories;

import com.devo.product.domain.ProductEntity;
import com.devo.product.domain.ProductInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Deprecated
public interface ProductInventoryRepository  extends JpaRepository<ProductInventoryEntity, UUID> {

    List<ProductInventoryEntity> findAllByProduct(ProductEntity productEntity);

}
