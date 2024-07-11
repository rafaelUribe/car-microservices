package com.example.inventoryservice.model.dto;

import lombok.Data;

@Data
public class CarModel {
    private Long id;
    private String name;
    private Brand brand;
}