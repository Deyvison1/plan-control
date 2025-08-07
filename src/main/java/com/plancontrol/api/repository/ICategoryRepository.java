package com.plancontrol.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.plancontrol.api.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAll(Pageable page);

    Long countBy();
}
