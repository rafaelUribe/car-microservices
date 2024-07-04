package com.example.cars_service.Controller;

import com.example.cars_service.Model.CarVersion;
import com.example.cars_service.Service.CarVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-versions")
public class CarVersionController {

    @Autowired
    private CarVersionService carVersionService;

    @PostMapping
    public ResponseEntity<CarVersion> createCarVersion(@RequestBody CarVersion carVersion) {
        return ResponseEntity.ok(carVersionService.createCarVersion(carVersion));
    }

    @GetMapping
    public ResponseEntity<List<CarVersion>> getAllCarVersions() {
        return ResponseEntity.ok(carVersionService.getAllCarVersions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarVersion> getCarVersionById(@PathVariable Long id) {
        return carVersionService.getCarVersionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarVersion> updateCarVersion(@PathVariable Long id, @RequestBody CarVersion carVersionDetails) {
        return ResponseEntity.ok(carVersionService.updateCarVersion(id, carVersionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarVersion(@PathVariable Long id) {
        carVersionService.deleteCarVersion(id);
        return ResponseEntity.noContent().build();
    }

}
