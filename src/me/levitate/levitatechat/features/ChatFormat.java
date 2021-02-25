package me.levitate.levitatechat.features;

import me.levitate.levitatechat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class ChatFormat implements Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {
        Player plr = event.getPlayer();
        String format = plugin.getConfig().getString("chat_format");
        format = format.replaceAll("&", "§");
        format = format.replaceAll("%username", plr.getName());
        format = format.replaceAll("%message", event.getMessage());

        event.setFormat(format);
    }
}
