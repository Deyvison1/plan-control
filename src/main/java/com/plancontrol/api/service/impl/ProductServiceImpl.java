package com.plancontrol.api.service.impl;

import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.exception.NotFoundException;
import com.plancontrol.api.mapper.IProductMapper;
import com.plancontrol.api.models.Product;
import com.plancontrol.api.repository.IProductRepository;
import com.plancontrol.api.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IProductMapper productMapper;

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = findById(productDTO.getId());
        Product productAAtualizar = mountProduct(productDTO, product);
        return productMapper.toDto(productRepository.save(productAAtualizar));
    }

    private Product mountProduct(ProductDTO productDTO, Product product) {
        product.setId(productDTO.getId());
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setValue(productDTO.getValue());
        product.setSpeedDownload(productDTO.getSpeedDownload());
        product.setSpeedUpload(productDTO.getSpeedUpload());
        product.setTaxaAdesao(productDTO.getTaxaAdesao());
        product.setValueWifi(productDTO.getValueWifi());
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = findById(productId);
        if (Objects.nonNull(product)) {
            productRepository.delete(product);
        }
    }

    private Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public ProductDTO findByIdDTO(Long id) {
        Product product = findById(id);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO addProduct(Product product) {
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public Page<Product> getAll(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }


    @Override
    public Long contarTodos() {
        return productRepository.countBy();
    }
}
