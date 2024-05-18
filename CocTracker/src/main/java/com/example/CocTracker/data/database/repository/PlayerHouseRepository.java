package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.PlayerHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerHouseRepository extends JpaRepository<PlayerHouseEntity, Integer> {

}
