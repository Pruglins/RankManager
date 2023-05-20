package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Create implements CommandExecutor {
    private final Main plugin;

    public Create(Main plugin) {
        this.plugin = plugin;
    }

    private void addNewRank(String name, String color) {
        plugin.getConfig().set("ranks." + name + ".color", color); // ranks.RANK_NAME.color
        plugin.getConfig().set("ranks." + name + ".permissions", "Null"); // ranks.RANK_NAME.permissions
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("create")) {
            if (sender instanceof Player player) {
                if (args.length >= 2) {
                    String name = args[0];
                    String color = args[1];
                    color = color.replace('&', '§');
                    addNewRank(name, color);
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Le rang " + plugin.getConfig().get("ranks." + name + ".color") + name + ChatColor.WHITE + " a été créé avec succès.");
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du grade et la couleur sous ce format : '&x' où 'x' est entre '0' et '9' ou de 'a' à 'f' !");
                }
            }
        }
        return false;
    }
}
