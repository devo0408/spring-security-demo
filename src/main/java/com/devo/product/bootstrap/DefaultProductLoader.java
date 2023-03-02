package com.devo.product.bootstrap;

import com.devo.product.domain.*;
import com.devo.product.repositories.CustomerRepository;
import com.devo.product.repositories.ProductInventoryRepository;
import com.devo.product.repositories.ProductOrderRepository;
import com.devo.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static com.devo.product.domain.OrderStatusEnum.NEW;

@RequiredArgsConstructor
@Component
public class DefaultProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductInventoryRepository productInventoryRepository;

    private static final int PRODUCT_COUNT = 100;
    private static final int ORDER_COUNT = 15;
    private static final int CUSTOMER_COUNT = 10;

    @Override
    public void run(String... args) {
        loadUsersData();
        loadProductData();
        loadProductInventoryData();
        loadOrderData();
    }

    private void loadProductData() {
        for(int i = 0; i < PRODUCT_COUNT; i++) {
            productRepository.save(
                ProductEntity.builder()
                    .title("test")
                    .type(ProductTypeEnum.PET_SUPPLIES)
                    .price(BigDecimal.ONE)
                    .build()

            );
        }
    }

    private void loadProductInventoryData() {
        productRepository.findAll().forEach(productEntity ->
            productInventoryRepository.save(
                ProductInventoryEntity.builder()
                    .productEntity(productEntity)
                    .quantityOnHand(new Random().nextInt(10))
                    .build()

            ));
    }

    private void loadOrderData() {
        for(int i = 0; i < ORDER_COUNT; i++) {
            productOrderRepository.save(
                ProductOrderEntity.builder()
                    .orderStatus(NEW)
                        .customer(getRandomCustomer())
                        .productOrderLines(
                            Set.of(
                                buildOrderLineEntity(),
                                buildOrderLineEntity(),
                                buildOrderLineEntity()
                            ))
                .build()
            );
        }
    }

    private void loadUsersData() {
        for(int i = 0; i < CUSTOMER_COUNT; i++) {
            customerRepository.save(
                CustomerEntity.builder()
                .customerName(UUID.randomUUID().toString())
                .build()
            );
        }
    }

    private CustomerEntity getRandomCustomer() {
        return customerRepository.findAll()
            .get(new Random().nextInt(CUSTOMER_COUNT - 1));
    }

    private ProductOrderLineEntity buildOrderLineEntity() {
        return ProductOrderLineEntity.builder()
            .productEntity(getRandomProduct())
            .quantityAllocated(new Random().nextInt(10))
            .build();
    }

    private ProductEntity getRandomProduct() {
        return productRepository.findAll()
            .get(new Random().nextInt(PRODUCT_COUNT - 1));
    }

}
