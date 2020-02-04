package NetWorth.Commands;

import NetWorth.Handler.Mongo;
import NetWorth.Main;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.TextFormat;

import java.util.Map;

public class NetWorthCommand extends PluginCommand {

    private Main plugin;

    public NetWorthCommand(String name, Main plugin) {
        super(name, plugin);
        this.setAliases(new String[]{"nw", "money"});
        this.plugin = plugin;
        this.description = "view your balance, send or receive money!";
    }

//    @Override
//    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
//        if (sender instanceof PlayerAPI) {
//
//            PlayerAPI player = (PlayerAPI) sender;
//
//            if (args.length == 0) {
//                sendHelp(player);
//            } else if (args.length == 1) {
//                if ("balance".equals(args[0])) {
//                    getBalance(player, player.getName());
//                } else {
//                    sendHelp(player);
//                }
//            } else if (args.length == 2) {
//                if (args[0].equals("balance")) {
//                    getBalance(player, args[1]);
//                } else {
//                    sendHelp(player);
//                }
//            } else if (args.length == 3) {
//                if (args[0].equals("send")) {
//                    try {
//                        sendMoney(player, args[1], Integer.parseInt(args[2]));
//                    } catch (NumberFormatException e) {
//                        player.sendMessage(TextFormat.DARK_RED+args[2] + TextFormat.RED + " is not a number");
//                    }
//                } else if (args[0].equals("setmoney")) {
//                    try {
//                        setMoney(player, args[1], Integer.parseInt(args[2]));
//                    } catch (NumberFormatException e) {
//                        player.sendMessage(TextFormat.DARK_RED+args[2] + TextFormat.RED + " is not a number");
//                    }
//                } else sendHelp(player);
//            }
//        } else {
//            sender.sendMessage("Commands can only be run from in-game");
//        }
//        return true;
//    }

//    void sendHelp(PlayerAPI player) {
//        player.sendMessage(TextFormat.GRAY+"[NetWorth]");
//        player.sendMessage("/nw help: this help page");
//        player.sendMessage("/nw balance: view your balance");
//        player.sendMessage("/nw balance <player>: view another player's balance");
//        player.sendMessage("/nw send <player> <amount>: send money to another player");
//        player.sendMessage(TextFormat.GRAY+"[========]");
//    }

//    void setMoney(PlayerAPI player, String receiver, int amount) {
//        if (player.hasPermission("networth.admin")) {
//            PlayerAPI var1 = (PlayerAPI) plugin.getServer().getPlayer(receiver);
//            if (var1 != null) {
//                if (player.getName().equals(var1.getName())) {
//                    player.setMoney(amount);
//                    player.sendMessage(TextFormat.YELLOW + "You set your balance to " + TextFormat.GOLD + String.format("%,d", amount));
//                } else {
//                    var1.setMoney(amount);
//                    player.sendMessage(TextFormat.YELLOW + "You set " + var1.getName() + "'s balance to " + TextFormat.GOLD + String.format("%,d", amount));
//                }
//            } else {
//                try {
//                    Map<String, Object> aliasMap = AliasPro.AliasPro.getPlayer(receiver);
//                    if (aliasMap != null) {
//                        String uuid = aliasMap.get("uuid").toString();
//                        Map<String, Object> objectMap = Mongo.query(uuid, "uuid");
//                        if (objectMap != null) {
//                            String name = objectMap.get("name").toString();
//                            PlayerAPI var2 = (PlayerAPI) plugin.getServer().getPlayer(name);
//                            if (var2 != null) {
//                                var2.setMoney(amount);
//                                player.sendMessage(TextFormat.YELLOW + "You set " + var2.getName() + "'s balance to " + TextFormat.GOLD + String.format("%,d", amount));
//                            } else {
//                                player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                            }
//                        } else {
//                            player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                        }
//                    } else {
//                        player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                    }
//                } catch (NoClassDefFoundError error) {
//                    player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                }
//            }
//        } else {
//            player.sendMessage(TextFormat.RED + "You do not have permission to use this command!");
//        }
//    }
//
//    void sendMoney(PlayerAPI player, String receiver, int amount) {
//        PlayerAPI var1 = (PlayerAPI) plugin.getServer().getPlayer(receiver);
//        String message = TextFormat.RED + "You do not have " + TextFormat.DARK_RED + String.format("%,d", amount) + TextFormat.RED + " to send";
//        if (var1 != null) {
//            if (!player.getName().equals(var1.getName())) {
//                if (player.getMoney() < amount) {
//                    player.sendMessage(message);
//                } else {
//                    player.subtractMoney(amount);
//                    var1.addMoney(amount);
//                    player.sendMessage(TextFormat.GREEN + "You sent " + TextFormat.DARK_GREEN + String.format("%,d", amount) + TextFormat.GREEN + " to " + var1.getName());
//                    player.sendMessage(TextFormat.GREEN + "You received " + TextFormat.DARK_GREEN + String.format("%,d", amount) + TextFormat.GREEN + " from " + player.getName());
//                }
//            } else {
//                player.sendMessage("You cannot send money to yourself silly...");
//            }
//        } else {
//            try {
//                Map<String, Object> aliasMap = AliasPro.AliasPro.getPlayer(receiver);
//                if (aliasMap != null) {
//                    String uuid = aliasMap.get("uuid").toString();
//                    Map<String, Object> objectMap = Mongo.query(uuid, "uuid");
//                    if (objectMap != null) {
//                        String name = objectMap.get("name").toString();
//                        PlayerAPI var2 = (PlayerAPI) plugin.getServer().getPlayer(name);
//                        if (var2 != null) {
//                            if (player.getMoney() < amount) {
//                                player.sendMessage(message);
//                            } else {
//                                player.subtractMoney(amount);
//                                var2.addMoney(amount);
//                                player.sendMessage(TextFormat.GREEN + "You sent " + TextFormat.DARK_GREEN + String.format("%,d", amount) + TextFormat.GREEN + " to " + var2.getName());
//                                player.sendMessage(TextFormat.GREEN + "You received " + TextFormat.DARK_GREEN + String.format("%,d", amount) + TextFormat.GREEN + " from " + player.getName());
//                            }
//                        } else {
//                            player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                        }
//                    } else {
//                        player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                    }
//                } else {
//                    player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//                }
//            } catch (NoClassDefFoundError error) {
//                player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + receiver);
//            }
//        }
//    }
//
//    void getBalance(PlayerAPI player, String query) {
//        PlayerAPI var1 = (PlayerAPI) plugin.getServer().getPlayer(query);
//        if (var1 != null) {
//            if (player.getName().equals(var1.getName())) {
//                player.sendMessage(TextFormat.GREEN +"Your balance is : " + TextFormat.DARK_GREEN + String.format("%,d", player.getMoney()));
//            } else {
//                player.sendMessage(TextFormat.GREEN+ var1.getName() + "'s balance is : " + TextFormat.DARK_GREEN + String.format("%,d", var1.getMoney()));
//            }
//        } else {
//            try {
//                Map<String, Object> aliasMap = AliasPro.AliasPro.getPlayer(query);
//                if (aliasMap != null) {
//                    String uuid = aliasMap.get("uuid").toString();
//                    Map<String, Object> objectMap = Mongo.query(uuid, "uuid");
//                    if (objectMap != null) {
//                        String name = objectMap.get("name").toString();
//                        PlayerAPI var2 = (PlayerAPI) plugin.getServer().getPlayer(name);
//                        if (var2 != null) {
//                            player.sendMessage(TextFormat.GREEN+ var2.getName() + "'s balance is : " + TextFormat.DARK_GREEN + String.format("%,d", var2.getMoney()));
//                        } else {
//                            player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + query);
//                        }
//                    } else {
//                        player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + query);
//                    }
//                } else {
//                    player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + query);
//                }
//            } catch (NoClassDefFoundError error) {
//                player.sendMessage(TextFormat.RED + "Could not find player " + TextFormat.DARK_RED + query);
//            }
//        }
//    }
}
