package com.parcialSB.services;

import com.parcialSB.models.Sale;
import com.parcialSB.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Integer id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }
}
