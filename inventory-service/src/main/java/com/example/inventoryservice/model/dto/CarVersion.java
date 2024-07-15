package com.example.inventoryservice.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarVersion {
    private Long id;
    private String versionName;
    private int modelYear;
    private CarModel carModel;
}