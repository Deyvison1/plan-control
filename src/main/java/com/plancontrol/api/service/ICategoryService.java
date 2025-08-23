package com.plancontrol.api.service;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.models.Category;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    Page<Category> getAll(Pageable pageable);

    List<CategoryDTO> getAll();

    CategoryDTO insert(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(UUID uuid);

    CategoryDTO findByIdDTO(UUID uuid);

    Long contarTodos();

    List<CategoryDTO> massCreation(MultipartFile file);

    Resource exportPage(Pageable pageable, String acceptHeader);
    
    CategoryDTO findByIdComplet(UUID uuidCategory);
}
