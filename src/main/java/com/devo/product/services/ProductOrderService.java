package com.devo.product.services;

import com.devo.product.web.model.ProductOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductOrderService {

    Page<ProductOrderDto> listOrders(UUID customerId, Pageable pageable);

    ProductOrderDto placeOrder(UUID customerId, ProductOrderDto beerOrderDto);

    ProductOrderDto getOrderById(UUID customerId, UUID orderId);

    ProductOrderDto pickupOrder(UUID customerId, UUID orderId);

}
