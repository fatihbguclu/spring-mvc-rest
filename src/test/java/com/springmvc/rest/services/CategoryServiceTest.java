package com.springmvc.rest.services;

import com.springmvc.rest.api.v1.mapper.CategoryMapper;
import com.springmvc.rest.api.v1.model.CategoryDTO;
import com.springmvc.rest.domain.Category;
import com.springmvc.rest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "joe";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception{

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        assertEquals(3,categoryDTOList.size());

    }

    @Test
    public void getCategoryByName() throws Exception{

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());
    }
}