package com.grimthedev.fileManager;

import com.grimthedev.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GlobalChunkManager {

    private static Main plugin;

    private static File file;
    private static FileConfiguration customFile;

    public GlobalChunkManager(Main plugin){
        this.plugin = plugin;
    }

    public static void createGlobalChunksFile() throws IOException {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), "globalchunks.yml");


        if (!file.exists()){
            try{
                file.createNewFile();
                writeDefaults();
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage(Color.RED + "[AdvancedClaims] globalchunks.yml dosyası oluşturulamadı!");
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
        customFile.save(file);
    }
    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            Bukkit.getConsoleSender().sendMessage(Color.RED + "[AdvancedClaims] globalchunks.yml dosyası kaydedilemedi!");
            e.printStackTrace();
        }
    }

    public static void writeDefaults(){

        File file = new File(plugin.getDataFolder(), "globalchunks.yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        fileConfiguration.createSection("players");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void addGlobalChunk(Player player, Chunk chunk){
        File file = new File(plugin.getDataFolder(), "globalchunks.yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if (!fileConfiguration.getConfigurationSection("players").contains(player.getUniqueId().toString())){
            fileConfiguration.getConfigurationSection("players").createSection(player.getUniqueId().toString());

            ArrayList<String> list = (ArrayList<String>) fileConfiguration.getStringList("players." + player.getUniqueId().toString());
            list.add(chunk.toString());
            fileConfiguration.set("players." + player.getUniqueId().toString(),list);

            try {
                fileConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            ArrayList<String> list1 = (ArrayList<String>) fileConfiguration.getStringList("players." + player.getUniqueId().toString());
            if (list1.contains(chunk.toString())){
                player.sendMessage("Lol this claim is claimed by you xd");
            }else{
                fileConfiguration.getConfigurationSection("players").createSection(player.getUniqueId().toString());

                ArrayList<String> list = (ArrayList<String>) fileConfiguration.getStringList("players." + player.getUniqueId().toString());
                list.add(chunk.toString());
                fileConfiguration.set("players." + player.getUniqueId().toString(),list);

                try {
                    fileConfiguration.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
