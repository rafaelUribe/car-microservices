package com.example.cars_service.service;

import com.example.cars_service.model.CarVersion;
import com.example.cars_service.model.dto.VersionInventoryDTO;

import java.util.List;
import java.util.Optional;

public interface CarVersionService {
    CarVersion createCarVersion(CarVersion carVersion);

    List<CarVersion> getAllCarVersions();

    Optional<CarVersion> getCarVersionById(Long id);

    List<CarVersion> getCarVersionByModelId(Long id);

    CarVersion updateCarVersion(Long id, CarVersion carVersionDetails);

    void deleteCarVersion(Long id);

    VersionInventoryDTO getCarVersionInventory(Long carVersionId);
}
