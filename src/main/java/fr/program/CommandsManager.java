package fr.program;

import fr.program.cmds.*;

public class CommandsManager {
    public static void init(Main plugin) {
        plugin.getCommand("create").setExecutor(new Create(plugin));
        plugin.getCommand("list").setExecutor(new List(plugin));
        plugin.getCommand("spr").setExecutor(new SetPlayerRank(plugin));
        plugin.getCommand("scr").setExecutor(new SetColorRank(plugin));
        plugin.getCommand("snr").setExecutor(new SetNameRank(plugin));
    }
}
