package config;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Map<String, Object> database;
    private boolean connected;

    private DatabaseConnection() {
        this.database = new HashMap<>();
        this.connected = true;
        System.out.println("✅ Conexión a base de datos establecida");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public String testConnection() {
        return connected ? "✅ Conectado" : "❌ Desconectado";
    }

    public void put(String key, Object value) {
        database.put(key, value);
    }

    public Object get(String key) {
        return database.get(key);
    }

    public void closeConnection() {
        connected = false;
        database.clear();
        System.out.println("🔌 Conexión cerrada");
    }
}