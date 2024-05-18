package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.WarLeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarLeagueRepository extends JpaRepository<WarLeagueEntity, Integer> {

}
