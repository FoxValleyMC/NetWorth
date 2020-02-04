package NetWorth.Events;

import NetWorth.Handler.Mongo;
import NetWorth.Main;
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
        System.out.println(player.toString());
        String uuid = player.getUniqueId().toString();
        if (Mongo.query(uuid, "uuid") == null) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("uuid", uuid);
            objectMap.put("name", player.getName());
            objectMap.put("balance", plugin.getConfig().getInt("default-balance"));
            Mongo.createNew(objectMap);
        }
    }
}
