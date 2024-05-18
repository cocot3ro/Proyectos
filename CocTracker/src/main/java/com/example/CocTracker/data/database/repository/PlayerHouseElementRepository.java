package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.PlayerHouseElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerHouseElementRepository extends JpaRepository<PlayerHouseElementEntity, Integer> {
}
