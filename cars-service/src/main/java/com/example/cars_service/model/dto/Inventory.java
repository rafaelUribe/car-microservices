package com.example.cars_service.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    private Long id;

    private Long carVersion;

    private Long quantity;

}
