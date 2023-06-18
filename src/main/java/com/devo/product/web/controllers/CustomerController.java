package com.devo.product.web.controllers;

import com.devo.product.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping(produces = {"application/json" })
  public ResponseEntity<List<Long>> listProducts() {
    log.debug("Listing customers");

    return ResponseEntity.ok(
        customerService.getAllCustomerIds()
    );
  }
}
