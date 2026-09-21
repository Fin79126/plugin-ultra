package com.example.pluginultra.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UltraCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Component.text("=== PluginUltra コマンド ===", NamedTextColor.GOLD));
            sender.sendMessage(Component.text("/ultra ping ", NamedTextColor.YELLOW)
                    .append(Component.text("- 動作確認 (Pong! を返信 & 効果音)", NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("/ultra heal ", NamedTextColor.YELLOW)
                    .append(Component.text("- 体力と満腹度を全回復", NamedTextColor.GRAY)));
            return true;
        }

        if (args[0].equalsIgnoreCase("ping")) {
            sender.sendMessage(Component.text("[PluginUltra] ", NamedTextColor.AQUA)
                    .append(Component.text("Pong! プラグインは正常に動作しています！", NamedTextColor.GREEN)));
            if (sender instanceof Player player) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("heal")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(Component.text("このコマンドはゲーム内のプレイヤーのみ実行できます。", NamedTextColor.RED));
                return true;
            }

            AttributeInstance maxHealthAttr = player.getAttribute(Attribute.MAX_HEALTH);
            double maxHealth = (maxHealthAttr != null) ? maxHealthAttr.getValue() : 20.0;
            player.setHealth(maxHealth);
            player.setFoodLevel(20);
            player.setSaturation(20.0f);

            player.sendMessage(Component.text("[PluginUltra] ", NamedTextColor.AQUA)
                    .append(Component.text("体力と満腹度を全回復しました！", NamedTextColor.LIGHT_PURPLE)));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.5f);
            return true;
        }

        sender.sendMessage(Component.text("不明なサブコマンドです。/ultra help で一覧を確認してください。", NamedTextColor.RED));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> subCommands = List.of("ping", "heal", "help");
            List<String> completions = new ArrayList<>();
            for (String sub : subCommands) {
                if (sub.toLowerCase().startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
            return completions;
        }
        return List.of();
    }
}
