package com.example.inventoryservice.model.dto;

import lombok.Data;

@Data
public class CarVersion {
    private Long id;
    private String versionName;
    private int modelYear;
    private CarModel carModel;
}