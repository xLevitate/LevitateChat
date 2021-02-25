package me.levitate.levitatechat.features;

import me.levitate.levitatechat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class AntiSpam implements Listener {
    Plugin plugin = Main.getPlugin(Main.class);

    public ArrayList<String> mc = new ArrayList<>();
    public HashMap<String, String> c = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!plugin.getConfig().getBoolean("antispam_enabled", true)) {
            return;
        }

        Player plr = event.getPlayer();

        int t = plugin.getConfig().getInt("antispam_timer", 1);
        String message = ChatColor.stripColor(event.getMessage());

        if (c.containsKey(plr.getName())) {
            String s = c.getOrDefault(plr.getName(), "123456789-987654321");

            if (!s.equalsIgnoreCase("123456789-987654321") && s.equalsIgnoreCase(message)) {
                plr.sendMessage("§7Please wait " + t + " seconds between sending the same chat message.");
                event.setCancelled(true);
                return;
            }
        }

        if (mc.contains(plr.getName())) {
            event.setCancelled(true);
            plr.sendMessage("§7Please wait at least 1 second before sending chat messages.");
            return;
        }

        c.put(plr.getName(), message);
        mc.add(plr.getName());
        Bukkit.getScheduler().runTaskLater(plugin, () -> mc.remove(plr.getName()), 20);
        Bukkit.getScheduler().runTaskLater(plugin, () -> c.remove(plr.getName(), message), (t + 3) * 20);
    }


}
