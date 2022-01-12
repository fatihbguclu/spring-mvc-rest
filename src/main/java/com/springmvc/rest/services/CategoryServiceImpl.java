package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.mapper.CategoryMapper;
import com.springmvc.rest.api.v1.model.CategoryDTO;
import com.springmvc.rest.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<CategoryDTO> categoryDTOList = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));

        return categoryDTO;

    }


}
