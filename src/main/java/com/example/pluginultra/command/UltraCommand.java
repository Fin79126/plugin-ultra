package com.example.pluginultra.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UltraCommand implements BasicCommand {

    @Override
    public void execute(@NotNull CommandSourceStack source, @NotNull String[] args) {
        CommandSender sender = source.getSender();

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Component.text("=== PluginUltra コマンド ===", NamedTextColor.GOLD));
            sender.sendMessage(Component.text("/ultra ping ", NamedTextColor.YELLOW)
                    .append(Component.text("- 動作確認 (Pong! を返信 & 効果音)", NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("/ultra heal ", NamedTextColor.YELLOW)
                    .append(Component.text("- 体力と満腹度を全回復", NamedTextColor.GRAY)));
            return;
        }

        if (args[0].equalsIgnoreCase("ping")) {
            sender.sendMessage(Component.text("[PluginUltra] ", NamedTextColor.AQUA)
                    .append(Component.text("Pong! プラグインは正常に動作しています！", NamedTextColor.GREEN)));
            Entity executor = source.getExecutor();
            if (executor instanceof Player player) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);
            }
            return;
        }

        if (args[0].equalsIgnoreCase("heal")) {
            Entity executor = source.getExecutor();
            if (!(executor instanceof Player player)) {
                sender.sendMessage(Component.text("このコマンドはゲーム内のプレイヤーのみ実行できます。", NamedTextColor.RED));
                return;
            }

            AttributeInstance maxHealthAttr = player.getAttribute(Attribute.MAX_HEALTH);
            double maxHealth = (maxHealthAttr != null) ? maxHealthAttr.getValue() : 20.0;
            player.setHealth(maxHealth);
            player.setFoodLevel(20);
            player.setSaturation(20.0f);

            player.sendMessage(Component.text("[PluginUltra] ", NamedTextColor.AQUA)
                    .append(Component.text("体力と満腹度を全回復しました！", NamedTextColor.LIGHT_PURPLE)));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.5f);
            return;
        }

        sender.sendMessage(Component.text("不明なサブコマンドです。/ultra help で一覧を確認してください。", NamedTextColor.RED));
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack source, @NotNull String[] args) {
        if (args.length <= 1) {
            String current = args.length == 1 ? args[0].toLowerCase() : "";
            List<String> subCommands = List.of("ping", "heal", "help");
            List<String> completions = new ArrayList<>();
            for (String sub : subCommands) {
                if (sub.startsWith(current)) {
                    completions.add(sub);
                }
            }
            return completions;
        }
        return List.of();
    }
}
