package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

final class Inventory {
    // String[] productos = {"Coca-Cola", "Coca-Cola Zero", "Coca-Cola Zero Zero", "Aquarius Naranja", "Aquarius Limón", "Nestea", "Kas Naranja", "Kas Limón", "7Up", "Tónica Schweppes", "Gaseosa", "Agua con gas", "Agua grande", "Agua pequeña", "1906", "Cerveza sin alcohol", "Cerveza sin alcohol tostada", "Cerveza sin alcohol limón", "Zumito de piña", "Zumito de melocotón", "Zumito de naranja", "Mosto", "biter kas", "Leche", "Barril cerveza", "Barril cerveza limón", "Zumo de piña", "Zumo multifrutas", "Zumo de naranja", "Vino blanco casero", "Vino tinto casero"};
    private final Map<String, Integer> INVENTORY;
    private Map<String, Integer> limites;

    Inventory(ResultSet resultSet) throws Exception {
        this.INVENTORY = new LinkedHashMap<>();
        this.limites = new HashMap<>();
        initInventoryAndLimits(resultSet);
    }

    private void initInventoryAndLimits(ResultSet resultSet) throws Exception {
        try {
            while(resultSet.next()) {
                this.INVENTORY.put(resultSet.getString("producto"), resultSet.getInt("cantidad"));
                this.limites.put(resultSet.getString("producto"), resultSet.getInt("limite"));
            }
        } catch (SQLException e) {
            throw new Exception("Error al cargar el inventario");
        }
    }

    Map<String, Integer> getINVENTORY() {
        return new LinkedHashMap<>(INVENTORY);
    }

    Map<String, Integer> getLimites() {
        return new HashMap<>(limites);
    }

    void setLimites(Map<String, Integer> newLimits) {
        this.limites.putAll(newLimits);
    }

    void loadInventory(ResultSet resultSet) throws Exception {
        try {
            while (resultSet.next()) {
                this.INVENTORY.put(resultSet.getString("producto"), resultSet.getInt("cantidad"));
            }
        } catch (SQLException e) {
            throw new Exception("Error al cargar el inventario");
        }
    }
}
