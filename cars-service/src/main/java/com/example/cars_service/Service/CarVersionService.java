package com.example.cars_service.Service;

import com.example.cars_service.Model.CarVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarVersionService {
    CarVersion createCarVersion(CarVersion carVersion);

    List<CarVersion> getAllCarVersions();

    Optional<CarVersion> getCarVersionById(Long id);

    CarVersion updateCarVersion(Long id, CarVersion carVersionDetails);

    void deleteCarVersion(Long id);
}
