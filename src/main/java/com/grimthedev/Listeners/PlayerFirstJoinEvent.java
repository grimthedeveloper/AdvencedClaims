package com.grimthedev.Listeners;

import com.grimthedev.fileManager.PlayerFileCreator;
import com.grimthedev.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerFirstJoinEvent implements Listener {

    private static Main plugin;

    public PlayerFirstJoinEvent(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public static void onFirstJoin(PlayerJoinEvent event) throws IOException {
        File file = new File(plugin.getDataFolder()+File.separator+"Players", event.getPlayer().getUniqueId().toString()+".yml");
        PlayerFileCreator fileCreator = new PlayerFileCreator(plugin);

        if (!file.exists()){
            PlayerFileCreator.createPlayerFile(event.getPlayer());
            PlayerFileCreator.writeDefaults(event.getPlayer());
        }else{
            event.getPlayer().sendMessage("You played before >:(");
        }

    }

}
