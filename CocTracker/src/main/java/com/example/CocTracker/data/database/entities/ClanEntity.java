package com.example.CocTracker.data.database.entities;

import com.example.CocTracker.core.enums.ClanType;
import com.example.CocTracker.core.enums.WarFrequencyType;
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
public class ClanEntity {
    @Id
    private String tag;
    private String name;
    private ClanType type;
    private String description;
    @OneToOne
    private LocationEntity location;
    private boolean isFamilyFriendly;
    @OneToOne
    private BadgeUrlsEntity badgeUrls;
    private int clanLevel;
    private int clanPoints;
    private int clanBuilderBasePoints;
    private int clanCapitalPoints;
    @OneToOne
    private CapitalLeagueEntity capitalLeague;
    private int requiredTrophies;
    private WarFrequencyType warFrequency;
    private int warWinStreak;
    private int warWins;
    private int warTies;
    private int warLosses;
    private boolean isWarLog;
    @OneToOne
    private WarLeagueEntity warLeague;
    private int members;
    @OneToMany
    private List<MemberEntity> memberList;
    @OneToMany
    private List<LabelEntity> labelList;
    private int requiredBuilderBaseTrophies;
    private int requiredTownHallLevel;
    @OneToOne
    private ClanCapitalEntity clanCapital;
    @OneToOne
    private ChatLanguageEntity chatLanguage;
}