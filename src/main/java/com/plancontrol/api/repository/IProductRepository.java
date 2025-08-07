package com.plancontrol.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.plancontrol.api.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable page);

    Long countBy();
}
