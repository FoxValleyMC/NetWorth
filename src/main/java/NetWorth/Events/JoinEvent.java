package NetWorth.Events;

import NetWorth.Main;
import NukkitDB.Provider.MongoDB;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;

public class JoinEvent implements Listener {

    private Main plugin;

    public JoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        PlayerAPI player = (PlayerAPI) event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String collection = plugin.getConfig().getString("collection");
        if (MongoDB.getDocument(MongoDB.getCollection(collection), "uuid", uuid) == null) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("uuid", uuid);
            objectMap.put("balance", plugin.getConfig().getInt("default-balance"));
            MongoDB.insertOne(objectMap, MongoDB.getCollection(collection));
        }
    }
}
