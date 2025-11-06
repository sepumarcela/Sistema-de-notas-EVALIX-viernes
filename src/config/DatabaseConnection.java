package config;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulación de una base de datos ficticia en memoria.
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Map<String, Object> dataStore; // "almacén" de tablas simuladas

    private DatabaseConnection() {
        dataStore = new HashMap<>();
        System.out.println("✓ Conexión ficticia creada (base de datos en memoria)");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /** Inserta o actualiza una “tabla” en memoria */
    public void put(String tableName, Object data) {
        dataStore.put(tableName, data);
    }

    /** Obtiene una “tabla” simulada */
    public Object get(String tableName) {
        return dataStore.get(tableName);
    }

    /** Cierra la conexión ficticia */
    public void closeConnection() {
        dataStore.clear();
        System.out.println("✓ Conexión ficticia cerrada correctamente");
    }

    /** Verifica si la “conexión” está activa */
    public boolean testConnection() {
        return dataStore != null;
    }
}
