package me.levitate.levitatechat.features;

import me.levitate.levitatechat.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;

public class Toggle implements CommandExecutor, Listener {

    public static boolean chatt;

    static {
        Toggle.chatt = true;
    }

    @EventHandler
    public void chatt1(final PlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!Toggle.chatt && !p.hasPermission("lchat.offspeak")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void chatt1(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!Toggle.chatt && !p.hasPermission("lchat.offspeak")) {
            e.setCancelled(true);
            p.sendMessage("§7The chat is currently disabled!");
        }
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (!sender.hasPermission("lchat.chat")) { // Checks if the player has the /chat permission.
                sender.sendMessage("§7You don't have permission to do that.");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("§7Use /chat (on/off/clear)");
                return true;
            }
            if (args[0].equalsIgnoreCase("on")) {
                if (Toggle.chatt) {
                    sender.sendMessage("§7The chat is already enabled.");
                    return true;
                }
                if (!Toggle.chatt) {
                    Toggle.chatt = true;
                    Bukkit.broadcastMessage("§7The chat has been enabled.");
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("off")) {
                if (!Toggle.chatt) {
                    sender.sendMessage("§7The chat is already disabled!");
                    return true;
                }
                if (Toggle.chatt) {
                    Toggle.chatt = false;
                    Bukkit.broadcastMessage("§7The chat has been disabled.");
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("clear")) {
                for (int i = 0; i < 130; ++i) {
                    Bukkit.broadcastMessage(" ");
                }
                Bukkit.broadcastMessage("§6The chat has been cleared!");
                return true;
            }
        }
        return false;
    }
}
