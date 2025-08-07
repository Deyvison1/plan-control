package com.plancontrol.api.service.impl;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.exception.NotFoundException;
import com.plancontrol.api.exception.UnsupportedFileException;
import com.plancontrol.api.file.exporter.contract.FileExporter;
import com.plancontrol.api.file.exporter.factory.FileExporterFactory;
import com.plancontrol.api.file.imported.contract.FileImported;
import com.plancontrol.api.file.imported.factory.FileImportedFactory;
import com.plancontrol.api.mapper.ICategoryMapper;
import com.plancontrol.api.models.Category;
import com.plancontrol.api.repository.ICategoryRepository;
import com.plancontrol.api.service.ICategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
	private final ICategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;
	private final FileImportedFactory importer;
	private final FileExporterFactory exporter;

	@Override
	public Page<Category> getAll(Pageable page) {
		return categoryRepository.findAll(page);
	}

	@Override
	public CategoryDTO insert(Category category) {
		return categoryMapper.toDto(categoryRepository.save(category));
	}

	@Override
	public Long contarTodos() {
		return categoryRepository.countBy();
	}

	private Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		Category category = findById(categoryDTO.getId());
		return categoryMapper.toDto(categoryRepository.save(montarCategory(categoryDTO, category)));
	}

	private Category montarCategory(CategoryDTO categoryDTO, Category category) {
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}

	@Override
	public void delete(Long categoryId) {
		Category categoryById = findById(categoryId);
		categoryRepository.delete(categoryById);
	}

	@Override
	public List<CategoryDTO> getAll() {
		return categoryMapper.toDto(categoryRepository.findAll());
	}

	public List<CategoryDTO> massCreation(MultipartFile file) {
		if (file.isEmpty())
			throw new UnsupportedFileException("Please set a valid file");

		try (InputStream inputStream = file.getInputStream()) {
			String fileName = Optional.ofNullable(file.getOriginalFilename())
					.orElseThrow(() -> new UnsupportedFileException("File name cannot be null"));

			FileImported fileImported = this.importer.getImporter(fileName);

			List<Category> entities = fileImported.importFile(inputStream).stream()
					.map(dto -> categoryRepository.save(categoryMapper.toEntity(dto))).toList();

			return categoryMapper.toDto(entities);
		} catch (Exception e) {
			throw new UnsupportedFileException("Error processing the file");
		}

	}

	public Resource exportPage(Pageable page, String acceptHeader) {
		var listCategoryDTO = categoryRepository.findAll(page).map(categoryMapper::toDto).getContent();
		try {
			FileExporter exporter = this.exporter.getImporter(acceptHeader);
			return exporter.exportFile(listCategoryDTO);
		} catch (Exception e) {
			throw new RuntimeException("Error during file export! ", e);
		}
	}

	@Override
	public CategoryDTO findByIdDTO(Long id) {
		Category category = findById(id);
		return categoryMapper.toDto(category);
	}

}
