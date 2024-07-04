package com.example.cars_service.Service.Impl;

import com.example.cars_service.Model.CarVersion;
import com.example.cars_service.Service.CarVersionService;
import com.example.cars_service.Repository.CarVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class CarVersionServiceImpl implements CarVersionService {

    @Autowired
    private CarVersionRepository carVersionRepository;

    @Override
    public CarVersion createCarVersion(CarVersion carVersion) {
        return carVersionRepository.save(carVersion);
    }

    @Override
    public List<CarVersion> getAllCarVersions() {
        return carVersionRepository.findAll();
    }

    @Override
    public Optional<CarVersion> getCarVersionById(Long id) {
        return carVersionRepository.findById(id);
    }

    @Override
    public CarVersion updateCarVersion(Long id, CarVersion carVersionDetails) {
        CarVersion carVersion = carVersionRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Version not found"));
        carVersion.setVersionName(carVersionDetails.getVersionName());
        carVersion.setCarModel(carVersionDetails.getCarModel());
        carVersion.setFullName(carVersionDetails.getCarModel().getBrand().getName() + " " + carVersionDetails.getCarModel().getName() + " " + carVersionDetails.getVersionName());
        return carVersionRepository.save(carVersion);
    }

    @Override
    public void deleteCarVersion(Long id) {
        CarVersion carVersion = carVersionRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Version not found"));
        carVersionRepository.delete(carVersion);
    }

}
