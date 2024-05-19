package com.cocot3ro.coctracker.coctracker.data.database.entities;

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
public class LeagueEntity {
    @Id
    private int id;
    private String name;
    @OneToOne
    private IconUrlsEntity iconUrls;
}
