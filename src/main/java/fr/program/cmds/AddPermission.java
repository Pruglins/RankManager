package fr.program.cmds;

import fr.program.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class AddPermission implements CommandExecutor {
    private final Main plugin;

    public AddPermission(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("addperm")) {
            if (sender instanceof Player player) {
                if (args.length >= 2) {
                    String name = args[0];
                    String permission = args[1];

                    FileConfiguration config = plugin.getConfig();
                    List<String> permissions = config.getStringList("ranks." + name + ".permissions");
                    permissions.add(permission);
                    config.set("ranks." + name + ".permissions", permissions);
                    plugin.saveConfig();
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "La permission " + permission + " a été ajouté au rang " + config.getString("ranks." + name + ".color") + name + ChatColor.WHITE + ".");
                    return true;
                } else {
                    player.sendMessage("[" + ChatColor.BLUE + "Rank Manager" + ChatColor.WHITE + "] "
                            + "Veuillez préciser le nom du grade et la permission que vous voulez ajouter.");
                }
            }
        }
        return false;
    }
}
