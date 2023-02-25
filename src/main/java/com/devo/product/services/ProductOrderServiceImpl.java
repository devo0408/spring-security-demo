package com.devo.product.services;

import com.devo.product.domain.CustomerEntity;
import com.devo.product.exception.CustomerNotFoundException;
import com.devo.product.repositories.CustomerRepository;
import com.devo.product.repositories.ProductOrderRepository;
import com.devo.product.web.mappers.ProductOrderMapper;
import com.devo.product.web.model.ProductOrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final CustomerRepository customerRepository;
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
    public ProductOrderDto placeOrder(UUID customerId, ProductOrderDto beerOrderDto) {
        val customer = findCustomerRequired(customerId);



        return null;
    }

    @Override
    public ProductOrderDto getOrderById(UUID customerId, UUID orderId) {
        return null;
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {

    }

    private CustomerEntity findCustomerRequired(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
