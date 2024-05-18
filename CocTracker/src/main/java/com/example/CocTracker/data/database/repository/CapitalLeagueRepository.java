package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.CapitalLeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalLeagueRepository extends JpaRepository<CapitalLeagueEntity, Integer> {

}
