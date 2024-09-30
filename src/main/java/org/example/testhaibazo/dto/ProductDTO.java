package org.example.testhaibazo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer stock;
    private String size;
    private String color;
    private Long categoryId;
}
