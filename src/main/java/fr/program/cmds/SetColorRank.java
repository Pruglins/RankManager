package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class SetColorRank implements CommandExecutor {
    private final Main plugin;
    public SetColorRank(Main plugin) {
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
    private void setColorRank(String name, String color) {
        plugin.getConfig().set("ranks." + name + ".color", color); // ranks.RANK_NAME.color
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("scr")) {
            if (sender instanceof Player player) {
                if (args.length >= 2) {
                    String name = args[0];
                    String color = args[1];
                    color = color.replace('&', '§');
                    if (itExist(name)) {
                        setColorRank(name, color);
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "La couleur du rang " + plugin.getConfig().get("ranks." + name + ".color") + name + ChatColor.WHITE + " a été changé avec succès.");
                    } else {
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "Le rang " + ChatColor.RED + name + ChatColor.WHITE + " n'existe pas.");
                    }
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du grade et la couleur sous ce format : '&x' où 'x' est entre '0' et '9' ou de 'a' à 'f' !");
                }
            }
        }
        return false;
    }
}
