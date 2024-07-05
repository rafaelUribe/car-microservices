package com.example.cars_service.Service;

import com.example.cars_service.Model.CarModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CarModelService {
    CarModel createCarModel(CarModel carModel);

    List<CarModel> getAllCarModels();

    Optional<CarModel> getCarModelById(Long id);

    CarModel updateCarModel(Long id, CarModel carModelDetails);

    void deleteCarModel(Long id);
}
