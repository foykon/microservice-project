package com.BitProject.reportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private boolean isDeleted = false;

    private String name;

    private double price;

    private int stock;

    private String description;

}
