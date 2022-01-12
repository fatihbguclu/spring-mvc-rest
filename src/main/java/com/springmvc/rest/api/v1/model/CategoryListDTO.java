package com.springmvc.rest.api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categoryDTOList;

}
