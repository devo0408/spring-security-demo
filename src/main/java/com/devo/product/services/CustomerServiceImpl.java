package com.devo.product.services;

import com.devo.product.domain.CustomerEntity;
import com.devo.product.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public List<Integer> getAllCustomerIds() {
    return customerRepository.findAll().stream()
        .map(CustomerEntity::getId)
        .collect(toList());
  }
}
