package com.parcialSB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private Integer stok;

    @Column(nullable = false)
    private double price;


    @ManyToMany(mappedBy = "products")
    private List<Sale> sales;

    public Product(){
        sales = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Sale> getSale() {
        return sales;
    }

    public void setSale(List<Sale> sales) {
        this.sales = sales;
    }
}
