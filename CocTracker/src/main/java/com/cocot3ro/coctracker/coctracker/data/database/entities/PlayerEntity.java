package com.cocot3ro.coctracker.coctracker.data.database.entities;

import com.cocot3ro.coctracker.coctracker.core.enums.RoleType;
import com.cocot3ro.coctracker.coctracker.core.enums.WarPreferenceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerEntity {
    @OneToOne
    private LeagueEntity league;
    @OneToOne
    private BuilderBaseLeagueEntity builderBaseLeague;
    @OneToOne
    private ClanEntity clan;
    private RoleType role;
    private WarPreferenceType warPreference;
    private int attackWins;
    private int defenseWins;
    private int townHallLevel;
    private int townHallWeaponLevel;
    @OneToOne
    private PlayerLegendStatisticsEntity legendStatistics;
    @OneToMany
    private List<PlayerItemLevelEntity> troops;

    @Id
    private String tag;
    private String name;
    private int expLevel;
    private int trophies;
    private int bestTrophies;
    private int warStars;
    private int builderHallLevel;
    private int builderBaseTrophies;
    private int bestBuilderBaseTrophies;
    private int donations;
    private int donationsReceived;
    private int clanCapitalContributions;
}
