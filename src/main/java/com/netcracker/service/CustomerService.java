package com.netcracker.service;

import com.netcracker.domain.Customer;
import com.netcracker.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public String add(Customer customer) {
        customerRepository.save(customer);
        return "Successfully added";
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            return false;
        }
        Customer customer = customerOptional.get();
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public String deleteAll() {
        customerRepository.deleteAll();
        return "All customers deleted";
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        }
        return null;
    }
}
