package com.devo.product.repositories;

import com.devo.product.domain.CustomerEntity;
import com.devo.product.domain.OrderStatusEnum;
import com.devo.product.domain.ProductOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, UUID> {

    Page<ProductOrderEntity> findAllByCustomer(CustomerEntity customer, Pageable pageable);

    List<ProductOrderEntity> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

}
