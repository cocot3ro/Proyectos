package com.cocot3ro.coctracker.coctracker.data.database.entities;

import com.example.CocTracker.core.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberEntity {
    @Id
    private String tag;
    private String name;
    private RoleType role;
    private int townHallLevel;
    private int expLevel;
    @OneToOne
    private LeagueEntity league;
    private int trophies;
    private int builderBaseTrophies;
    private int clanRank;
    private int previousClanRank;
    private int donations;
    private int donationsReceived;
    @OneToOne
    private PlayerHouseEntity playerHouse;
    @OneToOne
    private BuilderBaseLeagueEntity builderBaseLeague;
}
