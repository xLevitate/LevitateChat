package me.levitate.levitatechat.features;

import me.levitate.levitatechat.Main;
import org.bukkit.plugin.Plugin;
import java.util.Random;
import org.bukkit.Bukkit;

public class AutomaticMessage {

    public static String[] messages;

    static {
        AutomaticMessage.messages = new String[] {Main.getInstance().getConfig().getString("Message1").replace("&", "§"),
                Main.getInstance().getConfig().getString("Message2").replace("&", "§" ),
                Main.getInstance().getConfig().getString("Message3").replace("&", "§" ),
                Main.getInstance().getConfig().getString("Message4").replace("&", "§" ),
                Main.getInstance().getConfig().getString("Message5").replace("&", "§" )};
    }

    public static void AutoMessage() {
        Plugin plugin = Main.getPlugin(Main.class);
        if (!plugin.getConfig().getBoolean("automessages_enabled", true)) {
            return;
        }
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(messages[new Random().nextInt(5)]);
            }
        }, 0L, Main.getInstance().getConfig().getInt("MessageDelay") * 20L);
    }

}
