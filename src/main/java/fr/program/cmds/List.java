package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class List implements CommandExecutor {
    private final Main plugin;

    public List(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("list")) {
            if (sender instanceof Player player) {
                FileConfiguration config = plugin.getConfig();
                ConfigurationSection allRanksSection = config.getConfigurationSection("ranks");
                player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] " + "La liste des rangs : ");
                if (allRanksSection != null) {
                    for (String keys_rank : allRanksSection.getKeys(false)) {
                        if (allRanksSection.getConfigurationSection(keys_rank) != null) {
                            ConfigurationSection rank_section = allRanksSection.getConfigurationSection(keys_rank);
                            if (rank_section.getString("color") != null) {
                                String color = rank_section.getString("color");
                                if (rank_section.getString("permissions") != null) {
                                    String permissions = rank_section.getString("permissions");
                                    player.sendMessage("Le rang "+ color + keys_rank + ChatColor.WHITE + " a pour permissions " + ChatColor.YELLOW  + permissions + ChatColor.WHITE + ".");
                                } else {
                                    player.sendMessage("Le rang " + color + keys_rank  + ChatColor.WHITE + " n'a pas de permissions.");
                                }
                            } else {
                                player.sendMessage("Les données du rang " + keys_rank + " sont incomplètes.");
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}