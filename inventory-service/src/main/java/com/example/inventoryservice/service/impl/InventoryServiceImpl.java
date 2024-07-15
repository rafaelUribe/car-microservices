package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.model.dto.CarVersion;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public Object getCarDetails(Long carId) {
        try {
            CarVersion carVersion = webClient.get()
                    .uri("/api/cars/car-versions/{id}", carId)
                    .retrieve()
                    .bodyToMono(CarVersion.class)
                    .block();

            Inventory inventory = inventoryRepository.findByCarVersion(carId);

            Map<String, Object> response = new HashMap<>();
            response.put("carVersion", carVersion);
            response.put("inventory", inventory.getQuantity());

            return response;
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error fetching car details", e);
        }
    }

    @Override
    public Inventory updateQuantity(Long carVersionId, String action) {
        Inventory inventory = inventoryRepository.findByCarVersion(carVersionId);
        if (inventory != null) {
            if ("add".equalsIgnoreCase(action)) {
                inventory.setQuantity(inventory.getQuantity() + 1);
            } else if ("subtract".equalsIgnoreCase(action) && inventory.getQuantity() > 0) {
                inventory.setQuantity(inventory.getQuantity() - 1);
            }
            return inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Inventory not found for car version ID: " + carVersionId);
        }
    }

    @Override
    public Inventory getInventoryByVersion(Long carVersionId){
        return inventoryRepository.findByCarVersion(carVersionId);
    }


}
