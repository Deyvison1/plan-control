package com.plancontrol.api.service;

import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    ProductDTO addProduct(Product product);

    ProductDTO updateProduct(ProductDTO Product);

    Page<Product> getAll(Pageable page);

    List<ProductDTO> getAll();

    void deleteProduct(Long id);

    ProductDTO findByIdDTO(Long id);

    Long contarTodos();
}
