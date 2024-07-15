package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface InventoryService {


    List<Inventory> getAllInventories();

    Inventory getInventoryById(Long id);

    Inventory saveInventory(Inventory inventory);

    void deleteInventory(Long id);

    Object getCarDetails(Long carId);

    Inventory updateQuantity(Long carVersionId, String action);

    Inventory getInventoryByVersion(Long carVersionId);
}

