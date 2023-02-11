package com.devo.product.bootstrap;

import com.devo.product.domain.ProductEntity;
import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.repositories.ProductInventoryRepository;
import com.devo.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class DefaultProductLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductInventoryRepository productInventoryRepository;

    @Override
    public void run(String... args) {
        loadProductData();
    }

    private void loadProductData() {
        for(int i = 0; i < 100; i++) {
            productRepository.save(
                    ProductEntity.builder()
                            .title("test")
                            .type(ProductTypeEnum.PET_SUPPLIES)
                            .price(BigDecimal.ONE)
                            .build()
            );
        }
    }
}
