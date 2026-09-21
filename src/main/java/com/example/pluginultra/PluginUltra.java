package com.example.pluginultra;

import com.example.pluginultra.command.UltraCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginUltra extends JavaPlugin {

    @Override
    public void onEnable() {
        // Paper公式の registerCommand を使用して登録
        this.registerCommand("ultra", new UltraCommand());
        getLogger().info("Registered command: /ultra");
        getLogger().info("PluginUltra has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PluginUltra has been disabled!");
    }
}
