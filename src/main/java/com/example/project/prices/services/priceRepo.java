package com.example.project.prices.services;

import com.example.project.prices.data.price;
import org.springframework.data.repository.CrudRepository;

public interface priceRepo extends CrudRepository<price,String> {
}
