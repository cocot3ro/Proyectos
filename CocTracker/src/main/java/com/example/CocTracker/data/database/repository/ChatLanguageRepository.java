package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.ChatLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLanguageRepository extends JpaRepository<ChatLanguageEntity, Integer> {
}
