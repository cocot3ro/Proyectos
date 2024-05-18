package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.BuilderBaseLeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuilderBaseLeagueRepository extends JpaRepository<BuilderBaseLeagueEntity, Integer> {

}
