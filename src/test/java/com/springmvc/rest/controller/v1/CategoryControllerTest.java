package com.springmvc.rest.controller.v1;

import com.springmvc.rest.api.v1.model.CategoryDTO;
import com.springmvc.rest.api.v1.model.CategoryListDTO;
import com.springmvc.rest.controller.v1.CategoryController;
import com.springmvc.rest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class CategoryControllerTest {

    public static final String NAME  = "Joe";
    public static final String NAME1  = "Biden";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName(NAME);
        categoryDTO1.setId(1L);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName(NAME1);
        categoryDTO2.setId(2L);

        List<CategoryDTO> categoryDTOList = Arrays.asList(categoryDTO1,categoryDTO2);

        CategoryListDTO categoryListDTO = new CategoryListDTO(categoryDTOList);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryDTOList",hasSize(2)));


    }

    @Test
    void getCategoryByName() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);
        categoryDTO.setId(1L);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(
                get("/api/v1/categories/Joe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));

    }

}