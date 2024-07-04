package com.example.cars_service.Repository;

import com.example.cars_service.Model.CarVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarVersionRepository extends JpaRepository<CarVersion, Long> {
}
