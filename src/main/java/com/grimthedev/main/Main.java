package com.grimthedev.main;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import com.grimthedev.Listeners.PlayerFirstJoinEvent;
import com.grimthedev.chunkManager.ChunkListener;
import com.grimthedev.chunkManager.PlayerMovmentListener;
import com.grimthedev.examples.addEXAMPLE;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ChunkListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerMovmentListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerFirstJoinEvent(this),this);
        getCommand("add").setExecutor(new addEXAMPLE(this));


    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Main getInstance(){return instance;}
}
