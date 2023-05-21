package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RemovePlayerRank implements CommandExecutor {
    private final Main plugin;

    public RemovePlayerRank(Main plugin) {
        this.plugin = plugin;
    }

    private boolean itExist(String r) {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection allRanksSection = config.getConfigurationSection("ranks");
        if (allRanksSection != null) {
            if (allRanksSection.contains(r)) {
                return true;
            }
        }
        return false;
    }
    private void setRank(String name) {
        plugin.getConfig().set("players." + name + ".rank", null); // ranks.RANK_NAME.color
        plugin.saveConfig();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rpr")) {
            if (sender instanceof Player player) {
                FileConfiguration config = plugin.getConfig();
                if (args.length >= 1) {
                    String player_name = args[0];
                    String rank_name = config.getString("players." + player_name + ".rank");
                    setRank(player_name);
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Le rang " + config.get("ranks." + rank_name + ".color") + rank_name + ChatColor.WHITE + " a été retiré au joueur " + ChatColor.YELLOW + ChatColor.BOLD + player_name + ChatColor.RESET +  ".");
                    return true;
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du joueur !");
                }
            }
        }
        return false;
    }
}
