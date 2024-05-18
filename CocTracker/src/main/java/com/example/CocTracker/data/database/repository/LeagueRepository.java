package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueEntity, Integer> {
}
