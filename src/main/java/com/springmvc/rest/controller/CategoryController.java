package com.springmvc.rest.controller;

import com.springmvc.rest.api.v1.model.CategoryDTO;
import com.springmvc.rest.api.v1.model.CategoryListDTO;
import com.springmvc.rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        CategoryListDTO categoryListDTO = new CategoryListDTO(categoryService.getAllCategories());

        ResponseEntity<CategoryListDTO> response = new ResponseEntity<>(categoryListDTO, HttpStatus.OK);

        return response;
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){

        CategoryDTO categoryDTO = categoryService.getCategoryByName(name);

        ResponseEntity<CategoryDTO> response = new ResponseEntity<>(categoryDTO, HttpStatus.OK);

        return response;
    }

}










