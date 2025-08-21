package com.movieflix.movieflix.service;

import com.movieflix.movieflix.controller.request.CategoryRequest;
import com.movieflix.movieflix.controller.response.CategoryResponse;
import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.exceptions.CategoryNotFoundException;
import com.movieflix.movieflix.mapper.CategoryMapper;
import com.movieflix.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = repository.findAll();
        return categories
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
       Category category = repository.save(CategoryMapper.toCategory(categoryRequest));
       return CategoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse findById(Long id) {
        return CategoryMapper.toCategoryResponse(getCategoryOrThrow(id));
    }

    public void deleteCategoryById(Long id) {
        repository.delete(getCategoryOrThrow(id));
    }

    private Category getCategoryOrThrow(Long id) {
        return repository
                .findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }
}
