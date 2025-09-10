package com.plancontrol.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.models.Product;
import com.plancontrol.api.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final IProductService productService;

	@GetMapping
	public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
		Page<Product> listProduct = productService.getAll(pageable);
		return ResponseEntity.ok(listProduct);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}

	@PostMapping
	public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.addProduct(productDTO));
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<ProductDTO> update(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateProduct(uuid, productDTO));
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
		productService.deleteProduct(uuid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<ProductDTO> getById(@PathVariable UUID uuid) {
		return ResponseEntity.ok(productService.findByIdDTO(uuid));
	}
}