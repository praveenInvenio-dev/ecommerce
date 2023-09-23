package com.pavi.ecom.dto;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    private Long parentCategoryId;
    
    private int level;
}