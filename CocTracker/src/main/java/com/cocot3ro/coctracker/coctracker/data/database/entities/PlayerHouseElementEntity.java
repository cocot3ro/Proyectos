package com.cocot3ro.coctracker.coctracker.data.database.entities;

import com.example.CocTracker.core.enums.PlayerHouseElementType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerHouseElementEntity {
    @Id
    private int id;
    private PlayerHouseElementType type;
}
