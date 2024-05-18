package com.example.CocTracker.data.database.repository;

import com.example.CocTracker.data.database.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
