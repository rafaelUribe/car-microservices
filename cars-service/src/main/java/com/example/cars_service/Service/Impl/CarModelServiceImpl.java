package com.example.cars_service.Service.Impl;

import com.example.cars_service.Model.CarModel;
import com.example.cars_service.Repository.CarModelRepository;
import com.example.cars_service.Service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public CarModel createCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }

    @Override
    public Optional<CarModel> getCarModelById(Long id) {
        return carModelRepository.findById(id);
    }

    @Override
    public CarModel updateCarModel(Long id, CarModel carModelDetails) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Model not found"));
        carModel.setName(carModelDetails.getName());
        carModel.setBrand(carModelDetails.getBrand());
        return carModelRepository.save(carModel);
    }

    @Override
    public void deleteCarModel(Long id) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Model not found"));
        carModelRepository.delete(carModel);
    }

}
