package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetPlayerRank implements CommandExecutor {
    private final Main plugin;

    public SetPlayerRank(Main plugin) {
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
    private void setRank(String name, String r) {
        plugin.getConfig().set("players." + name + ".rank", r); // ranks.RANK_NAME.color
        plugin.saveConfig();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spr")) {
            if (sender instanceof Player player) {
                if (args.length >= 2) {
                    String player_name = args[0];
                    String r = args[1];
                    if (itExist(r)) {
                        setRank(player_name, r);
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "Le rang " + plugin.getConfig().get("ranks." + r + ".color") + r + ChatColor.WHITE + " a été ajouté au joueur " + ChatColor.YELLOW + ChatColor.BOLD + player_name + ChatColor.RESET +  ".");
                        return true;
                    } else {
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "Le rang " + ChatColor.RED + r + ChatColor.WHITE + " n'existe pas.");
                    }
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du joueur et le grade !");
                }
            }
        }
        return false;
    }
}
