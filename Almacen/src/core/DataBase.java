package core;

import java.sql.*;
import java.util.Map;

final class DataBase {
    final Connection CONNECTION;
    final Statement STATEMENT;

    DataBase() throws Exception {
        try {
            this.CONNECTION = DriverManager.getConnection("jdbc:mysql://aws.connect.psdb.cloud/proyectos?sslMode=VERIFY_IDENTITY", "qgpks0222eyf3qdpsbjt", "pscale_pw_JkD9UPWkMmLXC128vMEzxKgzsCzvEaSri59s735RWPg");
            this.STATEMENT = CONNECTION.createStatement();
        } catch (SQLException e) {
            throw new Exception("Error al conectar con la base de datos");
        }
    }

    ResultSet getInventory() throws Exception {
        ResultSet resultSet;
        try {
            resultSet = STATEMENT.executeQuery("SELECT * FROM almacen");
        } catch (SQLException e) {
            throw new Exception("Error al cargar el inventario");
        }
        return resultSet;
    }

    void updateInventory(Map<String, Integer> newInventory) throws Exception {
        for (Map.Entry<String, Integer> entry : newInventory.entrySet()) {
            try {
                STATEMENT.execute(String.format("UPDATE almacen SET cantidad='%s' WHERE producto='%s'", entry.getValue(), entry.getKey()));
            } catch (SQLException e) {
                throw new Exception("Error al actualizar el inventario");
            }
        }
    }

    void traer(Map<String, Integer> newInventory) throws Exception {
        for (Map.Entry<String, Integer> entry : newInventory.entrySet()) {
            try {
                STATEMENT.execute(String.format("UPDATE almacen SET cantidad=cantidad-%s WHERE producto='%s'", entry.getValue(), entry.getKey()));
            } catch (SQLException e) {
                throw new Exception("Error al actualizar el inventario");
            }
        }
    }

    void anhadir(Map<String, Integer> newInventory) throws Exception {
        for (Map.Entry<String, Integer> entry : newInventory.entrySet()) {
            try {
                STATEMENT.execute(String.format("UPDATE almacen SET cantidad=cantidad+%s WHERE producto='%s'", entry.getValue(), entry.getKey()));
            } catch (SQLException e) {
                throw new Exception("Error al actualizar el inventario");
            }
        }
    }

    void setLimites(Map<String, Integer> newLimits) throws Exception {
        for (Map.Entry<String, Integer> entry : newLimits.entrySet()) {
            try {
                STATEMENT.execute(String.format("UPDATE almacen SET limite=%s WHERE producto='%s'", entry.getValue(), entry.getKey()));
            } catch (SQLException e) {
                throw new Exception("Error al actualizar el inventario");
            }
        }
    }
}
