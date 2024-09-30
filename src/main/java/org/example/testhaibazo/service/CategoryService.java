package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    Optional<CategoryDTO> getCategoryById(Long id);

    CategoryDTO createCategory(CategoryDTO categoryDto);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDto);

    void deleteCategory(Long id);
}

