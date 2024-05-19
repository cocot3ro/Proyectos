package com.cocot3ro.coctracker.coctracker.data.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerLegendStatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private LegendLeagueTournamentSeasonResultEntity previousSeason;
    @OneToOne
    private LegendLeagueTournamentSeasonResultEntity previousBuilderBaseSeason;
    @OneToOne
    private LegendLeagueTournamentSeasonResultEntity bestBuilderBaseSeason;
    @OneToOne
    private LegendLeagueTournamentSeasonResultEntity currentSeason;
    @OneToOne
    private LegendLeagueTournamentSeasonResultEntity bestSeason;
    private int legendTrophies;
}
