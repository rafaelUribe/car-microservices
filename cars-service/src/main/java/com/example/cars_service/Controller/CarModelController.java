package com.example.cars_service.Controller;

import com.example.cars_service.Model.CarModel;
import com.example.cars_service.Service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-models")
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @PostMapping
    public ResponseEntity<CarModel> createCarModel(@RequestBody CarModel carModel) {
        return ResponseEntity.ok(carModelService.createCarModel(carModel));
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        return ResponseEntity.ok(carModelService.getAllCarModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarModelById(@PathVariable Long id) {
        return carModelService.getCarModelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCarModel(@PathVariable Long id, @RequestBody CarModel carModelDetails) {
        return ResponseEntity.ok(carModelService.updateCarModel(id, carModelDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        carModelService.deleteCarModel(id);
        return ResponseEntity.noContent().build();
    }
}
