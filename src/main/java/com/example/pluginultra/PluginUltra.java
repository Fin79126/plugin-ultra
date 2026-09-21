package com.example.pluginultra;

import com.example.pluginultra.command.UltraCommand;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public final class PluginUltra extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        getLogger().info("PluginUltra has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PluginUltra has been disabled!");
    }

    private void registerCommands() {
        UltraCommand ultraCommand = new UltraCommand();
        PluginCommand command = getCommand("ultra");

        if (command != null) {
            command.setExecutor(ultraCommand);
            command.setTabCompleter(ultraCommand);
            getLogger().info("Registered command: /ultra (via plugin.yml)");
        } else {
            // paper-plugin.yml のみで読み込まれた場合のフォールバック動的登録
            try {
                Field commandMapField = getServer().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                CommandMap commandMap = (CommandMap) commandMapField.get(getServer());

                Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                constructor.setAccessible(true);
                PluginCommand dynamicCmd = constructor.newInstance("ultra", this);

                dynamicCmd.setDescription("PluginUltra test command");
                dynamicCmd.setUsage("/ultra [ping|heal|help]");
                dynamicCmd.setExecutor(ultraCommand);
                dynamicCmd.setTabCompleter(ultraCommand);

                commandMap.register(getName().toLowerCase(), dynamicCmd);
                getLogger().info("Registered command: /ultra (dynamically via CommandMap)");
            } catch (Exception e) {
                getLogger().warning("Failed to register command /ultra: " + e.getMessage());
            }
        }
    }
}
