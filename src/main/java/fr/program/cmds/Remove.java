package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Remove implements CommandExecutor {
    private final Main plugin;

    public Remove(Main plugin) {
        this.plugin = plugin;
    }

    private void removeRank(FileConfiguration config, String name) {
        ConfigurationSection ranksSection = config.getConfigurationSection("ranks");
        if (ranksSection != null) {
            ranksSection.set(name, null);
            plugin.saveConfig();
        }
    }
    private void updateRanks(FileConfiguration config, String name) {
        ConfigurationSection playersSection = config.getConfigurationSection("players");
        if (playersSection != null) {
            for (String player : playersSection.getKeys(false)) {
                ConfigurationSection player_sec = playersSection.getConfigurationSection(player);
                if (player_sec != null && player_sec.contains("rank") && player_sec.getString("rank") != null) {
                    String rank = player_sec.getString("rank");
                    if (rank != null && rank.equals(name)) {
                        player_sec.set("rank", null);
                        plugin.saveConfig();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("remove")) {
            if (sender instanceof Player player) {
                FileConfiguration config = plugin.getConfig();

                if (args.length >= 1) {
                    String name = args[0];
                    removeRank(config, name);
                    updateRanks(config, name);
                    return true;
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du grade et la couleur sous ce format : '&x' où 'x' est entre '0' et '9' ou de 'a' à 'f' !");
                }
            }
        }
        return false;
    }
}
