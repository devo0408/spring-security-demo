package com.devo.product.web.controllers;

import com.devo.product.services.ProductOrderService;
import com.devo.product.web.model.ProductOrderDto;
import com.devo.product.web.model.write.ProductOrderCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@RestController
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @GetMapping(produces = { "application/json" })
    public ResponseEntity<Page<ProductOrderDto>> listOrders(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "customerId") UUID customerId
    ) {
        log.debug("Listing customers orders for customer {}", customerId);

        return ResponseEntity.ok(
                productOrderService.listOrders(customerId, pageable)
        );
    }

    @GetMapping(produces = { "application/json"}, path = {"{orderId}"} )
    public ResponseEntity<ProductOrderDto> getOrderById(
            @PathVariable("orderId") UUID orderId,
            @RequestParam(value = "customerId") UUID customerId
    ) {
        log.debug("Get customers order {} by id {}", customerId, orderId);

        return ResponseEntity.ok(
                productOrderService.getOrderById(customerId, orderId)
        );
    }

    @PutMapping(produces = { "application/json" })
    public ResponseEntity<ProductOrderDto> placeOrder(
            @RequestParam(value = "customerId") UUID customerId,
            @RequestBody ProductOrderCreateDto productOrderCreateDto
    ) {
        log.debug("Placing order {} for customers orders for customer {}", productOrderCreateDto, customerId);

        return ResponseEntity.ok(
                productOrderService.placeOrder(customerId, productOrderCreateDto)
        );
    }

    @PatchMapping(produces = { "application/json"}, path = {"{orderId}/pickup"} )
    public ResponseEntity<ProductOrderDto> pickupOrder(
            @PathVariable("orderId") UUID orderId,
            @RequestParam(value = "customerId") UUID customerId
    ) {
        log.debug("Pickup customers order {} by id {}", customerId, orderId);

        return ResponseEntity.ok(
                productOrderService.pickupOrder(customerId, orderId)
        );
    }
}
