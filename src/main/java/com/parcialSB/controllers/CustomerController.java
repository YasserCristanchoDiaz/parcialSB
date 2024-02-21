package com.parcialSB.controllers;

import com.parcialSB.models.Customer;
import com.parcialSB.response.ResponseHandler;
import com.parcialSB.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        try{
            List<Customer> result = customerService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try{
            Customer result = customerService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Customer customer) {
        try{
            Customer result = customerService.save(customer);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            Customer existingCustomer = customerService.findById(id);
            if (existingCustomer != null) {
                customer.setId(id);
                existingCustomer.setName(customer.getName());
                Customer updatedCustomer = customerService.save(customer);
                return ResponseHandler.generateResponse("Customer updated successfully", HttpStatus.OK, updatedCustomer);
            }
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Customer result = customerService.findById(id);
            if (result != null) {
                customerService.deleteById(id);
                return ResponseHandler.generateResponse("Customer deleted successfully", HttpStatus.OK, result);
            }
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
