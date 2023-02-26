package com.devo.product.services;

import com.devo.product.domain.CustomerEntity;
import com.devo.product.domain.ProductOrderEntity;
import com.devo.product.exception.CustomerNotFoundException;
import com.devo.product.exception.ProductOrderCreationException;
import com.devo.product.exception.ProductOrderNotFoundException;
import com.devo.product.repositories.CustomerRepository;
import com.devo.product.repositories.ProductOrderRepository;
import com.devo.product.web.mappers.ProductOrderMapper;
import com.devo.product.web.model.ProductOrderDto;
import com.devo.product.web.model.write.ProductOrderCreateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static com.devo.product.domain.OrderStatusEnum.NEW;
import static com.devo.product.domain.OrderStatusEnum.READY;
import static com.devo.product.domain.OrderStatusEnum.PICKED_UP;

@Slf4j
@Service
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final CustomerRepository customerRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;

    @Override
    public Page<ProductOrderDto> listOrders(UUID customerId, Pageable pageable) {
        val customer = findCustomerRequired(customerId);

        val customersOrderPage = productOrderRepository.findAllByCustomer(customer, pageable);

        val resultList = customersOrderPage
                .stream()
                .map(productOrderMapper::entityToDto)
                .collect(toList());

        return new PageImpl<>(
                resultList,
                PageRequest.of(
                        customersOrderPage.getPageable().getPageNumber(),
                        customersOrderPage.getPageable().getPageSize()
                ),
                customersOrderPage.getTotalElements()
        );
    }

    @Override
    public ProductOrderDto placeOrder(UUID customerId, ProductOrderCreateDto ProductOrderCreateDto) {
        val customer = findCustomerRequired(customerId);

        return Optional.of(ProductOrderCreateDto)
                .map(productOrderMapper::dtoToEntity)
                 .map(orderEntity -> orderEntity.withStatus(NEW))
                .map(orderEntity -> orderEntity.withCustomer(customer))
                .map(this::populateOrderToOrderLines)
                .map(productOrderRepository::saveAndFlush)
                .map(productOrderMapper::entityToDto)
                .orElseThrow(ProductOrderCreationException::new);

    }

    @Override
    public ProductOrderDto getOrderById(UUID customerId, UUID orderId) {
        val customer = findCustomerRequired(customerId);

        return productOrderRepository.findByIdAndCustomer(orderId, customer)
                .map(productOrderMapper::entityToDto)
                .orElseThrow(ProductOrderNotFoundException::new);
    }

    @Override
    public ProductOrderDto pickupOrder(UUID customerId, UUID orderId) {
        val customer = findCustomerRequired(customerId);

        return productOrderRepository.findByIdAndCustomer(orderId, customer)
                .filter(order -> READY.equals(order.getOrderStatus()))
                .map(order -> order.withStatus(PICKED_UP))
                .map(productOrderRepository::saveAndFlush)
                .map(productOrderMapper::entityToDto)
                .orElseThrow(ProductOrderNotFoundException::new);
    }

    private CustomerEntity findCustomerRequired(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }

    private ProductOrderEntity populateOrderToOrderLines(ProductOrderEntity productOrderEntity) {
        productOrderEntity.getProductOrderLines().forEach(line ->
                line.setProductOrderEntity(productOrderEntity)
        );
        return productOrderEntity;
    }
}
