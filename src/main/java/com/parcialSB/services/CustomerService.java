package com.parcialSB.services;

import com.parcialSB.models.Customer;
import com.parcialSB.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Integer id){
        Optional<Customer> optional = customerRepository.findById( id );

        return optional.isPresent() ? optional.get() : null;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }
}
