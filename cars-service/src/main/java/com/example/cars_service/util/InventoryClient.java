package com.example.cars_service.util;

import com.example.cars_service.model.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Inventory> createInventory(Inventory request) {
        return webClientBuilder.build()
                .post()
                .uri("http://inventory-service/api/inventory")
                .body(Mono.just(request), Inventory.class)
                .retrieve()
                .bodyToMono(Inventory.class);
    }
}
