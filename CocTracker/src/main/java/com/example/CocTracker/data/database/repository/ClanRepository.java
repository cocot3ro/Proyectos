package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.ClanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanRepository extends JpaRepository<ClanEntity, String> {
    
}
