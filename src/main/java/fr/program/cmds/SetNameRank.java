package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetNameRank implements CommandExecutor {
    private final Main plugin;
    public SetNameRank(Main plugin) {
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

    private void updateRanks(FileConfiguration config, String name, String new_name) {
        ConfigurationSection playersSection = config.getConfigurationSection("players");
        if (playersSection != null) {
            for (String player : playersSection.getKeys(false)) {
                ConfigurationSection player_sec = playersSection.getConfigurationSection(player);
                if (player_sec.contains("rank") && player_sec.getString("rank") != null) {
                    String rank = player_sec.getString("rank");
                    if (rank.equals(name)) {
                        player_sec.set("rank", new_name);
                        plugin.saveConfig();
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("snr")) {
            if (sender instanceof Player player) {
                FileConfiguration config = plugin.getConfig();

                if (args.length >= 2) {
                    String current_rank_name = args[0];
                    String new_rank_name = args[1];
                    if (itExist(current_rank_name)) {
                        String rank_color = config.getString("ranks." + current_rank_name + ".color");
                        String rank_perms = config.getString("ranks." + current_rank_name + ".permissions");
                        config.set("ranks." + current_rank_name, null);
                        config.set("ranks." + new_rank_name + ".color", rank_color); // ranks.RANK_NAME.color
                        config.set("ranks." + new_rank_name + ".permissions", rank_perms); // ranks.RANK_NAME.permissions
                        plugin.saveConfig();

                        updateRanks(config, current_rank_name, new_rank_name);

                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                        + "Le rang " + rank_color + new_rank_name + ChatColor.WHITE + " qui avait pour ancien nom " + rank_color + current_rank_name + ChatColor.WHITE + " a changé de nom avec succès.");
                    } else {
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                        + "Le rang " + ChatColor.RED + current_rank_name + ChatColor.WHITE + " n'existe pas.");
                    }
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] " + "Veuillez préciser le nom du rang et le nouveau nom pour ce dernier !");
                }
            }
        }
        return false;
    }
}
