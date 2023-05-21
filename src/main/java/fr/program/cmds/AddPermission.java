package fr.program.cmds;

import fr.program.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;

public class AddPermission implements CommandExecutor {
    private final Main plugin;

    public AddPermission(Main plugin) {
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

    private void updatePerms(FileConfiguration config, String rank_name) {
        ConfigurationSection playersSection = config.getConfigurationSection("players");
        if (playersSection != null) {
            for (String player : playersSection.getKeys(false)) {
                ConfigurationSection player_sec = playersSection.getConfigurationSection(player);
                if (player_sec.contains("rank") && player_sec.getString("rank") != null) {
                    String rank = player_sec.getString("rank");
                    if (rank.equals(rank_name)) {
                        Player p = Bukkit.getPlayer(player_sec.getName());

                        if (p != null) {
                            List<String> rank_perms = config.getStringList("ranks." + rank + ".permissions");

                            PermissionAttachment perm_attachment = p.addAttachment(plugin);

                            for (String perm : rank_perms) {
                                perm_attachment.setPermission(perm, true);
                            }
                        }

                        System.out.println(player_sec.getName());
                    }
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("addperm")) {
            if (sender instanceof Player player) {
                if (args.length >= 2) {
                    String name = args[0];
                    String permission = args[1];

                    if (itExist(name)) {
                        FileConfiguration config = plugin.getConfig();
                        List<String> permissions = config.getStringList("ranks." + name + ".permissions");
                        permissions.add(permission);
                        config.set("ranks." + name + ".permissions", permissions);
                        plugin.saveConfig();
                        updatePerms(config, name);
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "La permission " + permission + " a été ajouté au rang " + config.getString("ranks." + name + ".color") + name + ChatColor.WHITE + ".");
                        return true;
                    } else {
                        player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                                + "Le rang " + ChatColor.RED + name + ChatColor.WHITE + " n'existe pas.");
                        return false;
                    }
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du grade et la permission que vous voulez ajouter.");
                }
            }
        }
        return false;
    }
}
