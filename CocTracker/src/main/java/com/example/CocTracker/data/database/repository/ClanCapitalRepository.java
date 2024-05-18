package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.ClanCapitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanCapitalRepository extends JpaRepository<ClanCapitalEntity, Integer> {

}
