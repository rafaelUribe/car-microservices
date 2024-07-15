package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.saveInventory(inventory);
        return ResponseEntity.ok(savedInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            inventory.setCarVersion(inventoryDetails.getCarVersion());
            inventory.setQuantity(inventoryDetails.getQuantity());
            Inventory updatedInventory = inventoryService.saveInventory(inventory);
            return ResponseEntity.ok(updatedInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update-quantity/{carVersionId}")
    public ResponseEntity<Inventory> updateQuantity(@PathVariable Long carVersionId, @RequestParam("action") String action) {
        try {
            Inventory updatedInventory = inventoryService.updateQuantity(carVersionId, action);
            return ResponseEntity.ok(updatedInventory);
        } catch (Exception e) {
            logger.error("Error updating inventory quantity", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/byCarVersionId")
    public ResponseEntity<Inventory> getInventoryByCarVersionId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryByVersion(id));
    }

    @GetMapping("/car-details/{carId}")
    public ResponseEntity<Object> getCarDetails(@PathVariable Long carId) {
        try {
            Object carDetails = inventoryService.getCarDetails(carId);
            return ResponseEntity.ok(carDetails);
        } catch (WebClientResponseException.ServiceUnavailable e) {
            logger.error("Car service is unavailable", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Car service is currently unavailable. Please try again later.");
        } catch (Exception e) {
            logger.error("Error fetching car details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching car details.");
        }
    }
}