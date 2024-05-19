package com.cocot3ro.coctracker.coctracker.data.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LegendLeagueTournamentSeasonResultEntity {
    @Id
    private String id;
    private int rank;
    private int trophies;
}
