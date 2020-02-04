package NetWorth.Handler;

import NetWorth.Main;
import NukkitDB.NukkitDB;

import java.util.Map;

public class Mongo {

    public static Map<String, Object> query(String key, String fieldName) {
        String database = Main.getInstance().getConfig().getString("database");
        String collection = Main.getInstance().getConfig().getString("collection");
        return NukkitDB.query(key.toLowerCase(), fieldName, database, collection);
    }

    public static void createNew(Map<String, Object> objectMap) {
        String database = Main.getInstance().getConfig().getString("database");
        String collection = Main.getInstance().getConfig().getString("collection");
        NukkitDB.insertDocument(objectMap, database, collection);
    }

    public static void update(String query, String key, Integer value) {
        String database = Main.getInstance().getConfig().getString("database");
        String collection = Main.getInstance().getConfig().getString("collection");
        NukkitDB.updateDocument(query, "uuid", key.toLowerCase(), value, database, collection);
    }
}
