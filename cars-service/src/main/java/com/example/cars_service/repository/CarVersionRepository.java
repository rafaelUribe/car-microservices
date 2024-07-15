package com.example.cars_service.repository;

import com.example.cars_service.model.CarVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarVersionRepository extends JpaRepository<CarVersion, Long> {
    List<CarVersion> findByCarModelId(Long id);

}
