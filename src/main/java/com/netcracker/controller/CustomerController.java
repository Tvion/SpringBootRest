package com.netcracker.controller;

import com.netcracker.Status;
import com.netcracker.domain.Customer;
import com.netcracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/buyer", produces = "application/json")
    public ResponseEntity<?> getAllCustomers() {

        List<Customer> customers = customerService.findAll();

        if (customers.isEmpty()) {
            Status status = new Status("No customers");
            return new ResponseEntity<Status>(status, HttpStatus.OK);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }


    @PostMapping(value = "/buyer/add", consumes = "application/json")
    public ResponseEntity<Status> addNewCustomer(@RequestBody Customer customer) {
        customerService.add(customer);
        Status status = new Status("Customer is created");
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }


    @GetMapping(value = "/buyer/{id}")
    public ResponseEntity<?> addNewContact(@PathVariable Long id) {
        Customer customer = customerService.findById(id);

        if (customer == null) {
            Status status = new Status("No customer with id " + id);
            return new ResponseEntity<Status>(status, HttpStatus.OK);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/buyer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        boolean isDeleted = customerService.delete(id);
        Status status = new Status("Customer with id " + id + " successfully deleted");
        if (isDeleted) {
            return new ResponseEntity<Status>(status, HttpStatus.OK);
        }
        status.setStatus("No customer with id " + id);
        return new ResponseEntity<Status>(status, HttpStatus.OK);

    }

    @DeleteMapping(value = "/buyer")
    public ResponseEntity<Status> deleteAll() {
        customerService.deleteAll();
        Status status = new Status("All customers are deleted");
        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }


}
