package com.plancontrol.api.service;

import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductDTO addProduct(Product product);

    ProductDTO updateProduct(ProductDTO Product);

    Page<Product> getAll(Pageable page);

    List<ProductDTO> getAll();

    void deleteProduct(UUID uuid);

    ProductDTO findByIdDTO(UUID uuid);

    Long contarTodos();
}
