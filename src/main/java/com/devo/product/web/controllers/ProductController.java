package com.devo.product.web.controllers;

import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.services.ProductService;
import com.devo.product.web.model.ProductViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
@RestController
public class ProductController {

    private final ProductService productService;


    @GetMapping(produces = { "application/json" })
    public ResponseEntity<Page<ProductViewDto>> listProducts(@PageableDefault Pageable pageable,
                                                             @RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "productTypeEnum", required = false) ProductTypeEnum productTypeEnum) {
        log.debug("Listing products");

        val products = productService.findAllOptionalParameters(pageable, name, productTypeEnum);
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = {"{productUuid}"}, produces = { "application/json" })
    public ResponseEntity<ProductViewDto> getProductById(@PathVariable("productUuid") UUID productUuid){
        log.debug("Get Request for ProductId: " + productUuid);

        val product = productService.findProductByExternalUuidRequired(productUuid);
        return ResponseEntity.ok(product);
    }

}
