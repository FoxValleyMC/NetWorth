package NetWorth.API;

import NukkitDB.NukkitDB;
import cn.nukkit.Player;

public class NetworthAPI {

    public static int getBalance(Player player) {
        String database = NetWorth.Main.getInstance().getConfig().getString("database");
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUniqueId().toString(), "uuid", database, collection
                ).get("balance").toString()
        );
    }

    public static void setMoney(Player player, int amount) {
        String database = NetWorth.Main.getInstance().getConfig().getString("database");
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        NukkitDB.updateDocument(
                player.getUniqueId().toString(), "uuid", "balance", amount, database, collection
        );

    }

    public static void addMoney(Player player, int amount) {
        String database = NetWorth.Main.getInstance().getConfig().getString("database");
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        int currentBalance = getBalance(player);
        int newBalance = currentBalance + amount;
        NukkitDB.updateDocument(
                player.getUniqueId().toString(), "uuid", "balance", newBalance, database, collection
        );
    }

    public static void subtractMoney(Player player, int amount) {
        String database = NetWorth.Main.getInstance().getConfig().getString("database");
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        int currentBalance = getBalance(player);
        int newBalance;
        if (currentBalance <= 0) {
            newBalance = 0;
        } else {
            newBalance = currentBalance - amount;
        }
        NukkitDB.updateDocument(
                player.getUniqueId().toString(), "uuid", "balance", newBalance, database, collection
        );
    }

}
