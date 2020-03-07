package NetWorth.API;

import NukkitDB.Provider.MongoDB;
import cn.nukkit.Player;

public class NetworthAPI {

    public static int getBalance(Player player) {
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUniqueId().toString()
                ).get("balance").toString()
        );
    }

    public static void setMoney(Player player, int amount) {
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        String uuid = player.getUniqueId().toString();
        MongoDB.updateOne(
                MongoDB.getCollection(collection), "uuid", uuid, "balance", amount
        );
    }

    public static void addMoney(Player player, int amount) {
        String collection = NetWorth.Main.getInstance().getConfig().getString("collection");
        int currentBalance = getBalance(player);
        int newBalance = currentBalance + amount;
        String uuid = player.getUniqueId().toString();
        MongoDB.updateOne(
                MongoDB.getCollection(collection), "uuid", uuid, "balance", newBalance
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
        String uuid = player.getUniqueId().toString();
        MongoDB.updateOne(
                MongoDB.getCollection(collection), "uuid", uuid, "balance", newBalance
        );
    }

}
