package com.example.cars_service.service.Impl;

import com.example.cars_service.model.CarVersion;
import com.example.cars_service.model.dto.Inventory;
import com.example.cars_service.model.dto.VersionInventoryDTO;
import com.example.cars_service.service.CarVersionService;
import com.example.cars_service.repository.CarVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CarVersionServiceImpl implements CarVersionService {

    @Autowired
    private CarVersionRepository carVersionRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CarVersionServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public CarVersion createCarVersion(CarVersion carVersion) {
        CarVersion savedCarVersion = carVersionRepository.save(carVersion);

        createInventoryRecord(savedCarVersion);

        return savedCarVersion;
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
    public List<CarVersion> getCarVersionByModelId(Long id){
        return carVersionRepository.findByCarModelId(id);
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

    private void createInventoryRecord(CarVersion carVersion) {
        try {
            Inventory inventory = new Inventory();
            inventory.setCarVersion(carVersion.getId());
            inventory.setQuantity(0L);

            webClientBuilder.build().post()
                    .uri("http://inventory-service/api/inventory")
                    .body(Mono.just(inventory), Inventory.class)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create inventory record", e);
        }
    }

    @Override
    public VersionInventoryDTO getCarVersionInventory(Long carVersionId) {
        try {
            return webClientBuilder.build()
                    .post()
                    .uri("http://inventory-service/api/inventory/byCarVersionId?id=" + carVersionId)
                    .retrieve()
                    .bodyToMono(VersionInventoryDTO.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error fetching inventory details", e);
        }
    }
}
