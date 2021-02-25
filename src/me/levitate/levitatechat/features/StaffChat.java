package me.levitate.levitatechat.features;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.levitate.levitatechat.Main;

public class StaffChat implements CommandExecutor {

    Plugin plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("This command is only for players.");
        }

        Player plr = (Player)sender;

        if (!plugin.getConfig().getBoolean("staffchat_enabled", true)) {
            return false;
        }

        if(cmd.getName().equalsIgnoreCase("staffchat")) {
            if (plr.hasPermission("lchat.staffchat")) {
                if (args.length > 0) {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + "" + args[i];
                        message = ChatColor.translateAlternateColorCodes('&', message);
                    }
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("lchat.staffchat")) {
                            String sendMessage = chatFormat(message, plr);
                            String t = plr.getName();
                            Player target = Bukkit.getPlayer(t);
                            all.sendMessage(sendMessage);
                        }
                    }
                } else {
                    plr.sendMessage("§7Use: /staffchat <message>");
                }
            } else {
                plr.sendMessage("§7You don't have permission to use this command.");
            }
        }
        return false;
    }
    public String chatFormat(String msg, Player p){
        String format = plugin.getConfig().getString("staffchat_format");
        format = format.replaceAll("%username", p.getName());
        format = format.replaceAll("%message", msg);
        format = format.replaceAll("&", "§");
        return format;
    }
}
