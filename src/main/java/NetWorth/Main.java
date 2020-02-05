package NetWorth;

import NetWorth.Commands.NetWorthCommand;
import NetWorth.Events.JoinEvent;
import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    private static Main instance;

    @Override
    public void onLoad() {
        CommandMap commandMap = getServer().getCommandMap();
        commandMap.register("NetWorth", new NetWorthCommand("networth", this));
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        if (getConfig().getString("database").isEmpty() || getConfig().getString("collection").isEmpty()) {
            getLogger().error("Please edit config");
            getPluginLoader().disablePlugin(this);
            return;
        }
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

}
