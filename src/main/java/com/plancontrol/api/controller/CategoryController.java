package com.plancontrol.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.file.exporter.MediaTypes;
import com.plancontrol.api.models.Category;
import com.plancontrol.api.service.ICategoryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final ICategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAll(Pageable pageable) {
		Page<Category> listCategory = categoryService.getAll(pageable);
		final Long total = categoryService.contarTodos();

		HttpHeaders headers = new HttpHeaders();
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<Category>>(listCategory.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public List<CategoryDTO> getAll() {
		return categoryService.getAll();
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<CategoryDTO> findByIdComplet(@PathVariable UUID uuid) {
		return ResponseEntity.ok(categoryService.findByIdComplet(uuid));
	}

	@PostMapping
	public CategoryDTO insert(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.insert(categoryDTO);
	}

	@PutMapping
	public CategoryDTO update(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.update(categoryDTO);
	}

	@DeleteMapping("/{uuid}")
	public void delete(@PathVariable UUID uuid) {
		categoryService.delete(uuid);
	}

	@GetMapping("/import")
	public List<CategoryDTO> massCreation(@RequestParam("file") MultipartFile file) throws Exception {
		return categoryService.massCreation(file);
	}

	@GetMapping("/export-page")
	public ResponseEntity<Resource> exportPage(Pageable pageable, HttpServletRequest request) throws Exception {
		String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

		Resource file = categoryService.exportPage(pageable, acceptHeader);

		String contentType = acceptHeader != null ? acceptHeader : "application/octet-stream";
		String fileExtension = MediaTypes.APPLICATION_XLSX_VALUE.equalsIgnoreCase(contentType) ? ".xlsx" : ".csv";
		String fileName = "category_exported" + fileExtension;
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(file);
	}
}
