package fr.program;

import fr.program.cmds.Create;
import fr.program.cmds.List;
import fr.program.cmds.SetPlayerRank;

public class CommandsManager {
    public static void init(Main plugin) {
        plugin.getCommand("create").setExecutor(new Create(plugin));
        plugin.getCommand("list").setExecutor(new List(plugin));
        plugin.getCommand("spr").setExecutor(new SetPlayerRank(plugin));
    }
}
