package me.levitate.levitatechat;

import me.levitate.levitatechat.features.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.material.Command;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        AutomaticMessage.AutoMessage();
        this.saveDefaultConfig();

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    public void registerCommands() {
        this.getCommand("chat").setExecutor((CommandExecutor) new Toggle());
        this.getCommand("staffchat").setExecutor(new StaffChat());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new ChatFormat(),this);
        getServer().getPluginManager().registerEvents(new AntiSwear(),this);
        getServer().getPluginManager().registerEvents(new AntiSpam(),this);
        Bukkit.getPluginManager().registerEvents((Listener)new Toggle(), (Plugin)this);
    }
}
