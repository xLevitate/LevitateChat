package me.levitate.levitatechat.features;

import me.levitate.levitatechat.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class AntiSwear implements Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (!plugin.getConfig().getBoolean("antiswear_enabled", true)) {
            return;
        }
        for(String word: e.getMessage().split(" ")) {
            if (plugin.getConfig().getStringList("blockedwords").contains(word)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§7Please don't swear!");
            }
        }
    }
}
