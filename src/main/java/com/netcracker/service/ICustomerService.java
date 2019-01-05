package com.netcracker.service;

import com.netcracker.domain.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    String add(Customer customer);

    boolean delete(Long id);

    String deleteAll();

    Customer findById(Long id);
}
