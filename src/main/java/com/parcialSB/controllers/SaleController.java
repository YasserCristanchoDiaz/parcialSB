package com.parcialSB.controllers;

import com.parcialSB.models.Customer;
import com.parcialSB.models.Sale;
import com.parcialSB.response.ResponseHandler;
import com.parcialSB.services.CustomerService;
import com.parcialSB.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        try {
            List<Sale> result = saleService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            Sale result = saleService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Sale sale, @PathVariable Integer id ) {
        try {
            Customer find = customerService.findById(id);
            if ( find!= null) {
                sale.setCustomer(find);
                Sale result = saleService.save(sale);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }
            return ResponseHandler.generateResponse("No found", HttpStatus.INTERNAL_SERVER_ERROR, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
