package com.plancontrol.api.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.dto.ProductFilterDTO;
import com.plancontrol.api.exception.NotFoundException;
import com.plancontrol.api.mapper.ICategoryMapper;
import com.plancontrol.api.mapper.IProductMapper;
import com.plancontrol.api.models.Product;
import com.plancontrol.api.repository.IProductRepository;
import com.plancontrol.api.repository.ProductSpecification;
import com.plancontrol.api.service.IProductService;
import com.plancontrol.api.service.ITokenService;
import com.plancontrol.api.service.client.IUserClientService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IProductMapper productMapper;
    private final ICategoryMapper categoryMapper;
	private final IUserClientService userClientService;
	private final ITokenService tokenService;

    @Override
    public ProductDTO updateProduct(UUID uuid, ProductDTO productDTO) {
        Product product = findById(uuid);
        Product productAAtualizar = mountProduct(productDTO, product);
        return productMapper.toDto(productRepository.save(productAAtualizar));
    }

    private Product mountProduct(ProductDTO productDTO, Product product) {
    	DecodedJWT tokenDecoded = tokenService.decodedToken();
		UUID uuidUser = userClientService.findUUIDByNick(tokenDecoded.getSubject());
		product.setUserUpdateId(uuidUser);
        product.setUuid(productDTO.getUuid());
        product.setCategory(categoryMapper.toEntity(productDTO.getCategory()));
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
    public void deleteProduct(UUID uuid) {
        Product product = findById(uuid);
        if (Objects.nonNull(product)) {
            productRepository.delete(product);
        }
    }

    private Product findById(UUID uuid) {
        return productRepository.findById(uuid).orElseThrow(NotFoundException::new);
    }

    @Override
    public ProductDTO findByIdDTO(UUID uuid) {
        Product product = findById(uuid);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
    	Product entity = productMapper.toEntity(productDTO);
        return productMapper.toDto(productRepository.save(entity));
    }

    @Override
    public Page<ProductDTO> getAll(ProductFilterDTO filter, Pageable page) {
    	Specification<Product> spec = ProductSpecification.filterBy(filter);
	    Page<Product> entities = productRepository.findAll(spec, page);
		List<ProductDTO> dtos = productMapper.toDto(entities.getContent());
		return new PageImpl<>(dtos, page, entities.getTotalElements());
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
