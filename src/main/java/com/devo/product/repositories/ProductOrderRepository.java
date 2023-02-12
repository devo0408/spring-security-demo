package com.devo.product.repositories;

import com.devo.product.domain.OrderStatusEnum;
import com.devo.product.domain.ProductOrderEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.UUID;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, UUID> {

    List<ProductOrderEntity> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    ProductOrderEntity findOneById(UUID id);

}
