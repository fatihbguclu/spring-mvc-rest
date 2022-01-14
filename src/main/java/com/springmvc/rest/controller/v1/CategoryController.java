package com.springmvc.rest.controller.v1;

import com.springmvc.rest.api.v1.model.CategoryDTO;
import com.springmvc.rest.api.v1.model.CategoryListDTO;
import com.springmvc.rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories/";

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










