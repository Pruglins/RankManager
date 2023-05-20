package fr.program;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class RMEventListener implements Listener {
    private final Main plugin;
    public RMEventListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        FileConfiguration config = plugin.getConfig();
        String rank_name = config.getString("players." + player.getName() + ".rank");
        String rank_color = config.getString("ranks." + rank_name + ".color");

        event.setMessage("[" + rank_color + rank_name + ChatColor.WHITE + "] " + message);
        event.setCancelled(false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        String rank_name = config.getString("players." + player.getName() + ".rank");
        String rank_color = config.getString("ranks." + rank_name + ".color");
        event.setJoinMessage("[" + rank_color + rank_name + ChatColor.WHITE + "] " + ChatColor.BOLD + player.getName() + ChatColor.RESET + " a rejoint le serveur !");
    }
}
