package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.IconUrlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconUrlsRepository extends JpaRepository<IconUrlsEntity, Integer> {
}
