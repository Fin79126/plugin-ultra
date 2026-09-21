package com.example.pluginultra;

import org.bukkit.plugin.java.JavaPlugin;

public final class PluginUltra extends JavaPlugin {

    @Override
    public void onEnable() {
        // プラグイン有効化時の処理
        getLogger().info("PluginUltra has been enabled!");
    }

    @Override
    public void onDisable() {
        // プラグイン無効化時の処理
        getLogger().info("PluginUltra has been disabled!");
    }
}
