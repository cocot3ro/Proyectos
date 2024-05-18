package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Integer> {

}
