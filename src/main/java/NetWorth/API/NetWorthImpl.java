package NetWorth.API;

import cn.nukkit.Player;

public interface NetWorthImpl {

    int getMoney(Player player);

    void setMoney(Player player, int amount);

    void addMoney(Player player, int amount);

    void subtractMoney(Player player, int amount);

}
