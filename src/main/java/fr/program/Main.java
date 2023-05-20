package fr.program;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("[RankManager] Ready");

        saveDefaultConfig();

        CommandsManager.init(this);
    }

    @Override
    public void onDisable() {
        System.out.println("[RankManager] Stopped");
    }
}