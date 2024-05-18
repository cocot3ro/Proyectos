package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer> {

}
