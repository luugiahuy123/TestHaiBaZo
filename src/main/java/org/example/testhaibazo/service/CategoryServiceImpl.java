package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.CategoryDTO;
import org.example.testhaibazo.model.Category;
import org.example.testhaibazo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = convertToEntity(categoryDto);
        return convertToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            return convertToDto(categoryRepository.save(category));
        }).orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO convertToDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    private Category convertToEntity(CategoryDTO categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getName(), categoryDto.getDescription(), null);
    }
}
