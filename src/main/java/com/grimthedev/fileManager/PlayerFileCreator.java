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
import java.util.List;

public class PlayerFileCreator {

    private static Main plugin;

    private static File file;
    private static FileConfiguration customFile;

    public PlayerFileCreator(Main plugin){
        this.plugin = plugin;
    }

    public static void createPlayerFile(Player player) throws IOException {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder()+File.separator+"Players", player.getUniqueId().toString()+".yml");


        if (!file.exists()){
            try{
                file.createNewFile();
                writeDefaults(player);
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage(Color.RED + "[AdvancedClaims] Oyuncu dosyası oluşturulamadı!");
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
            Bukkit.getConsoleSender().sendMessage(Color.RED + "[AdvancedClaims] Oyuncu dosyası kaydedilemedi!");
            e.printStackTrace();
        }
    }

    public static void writeDefaults(Player player){

        File file = new File(plugin.getDataFolder() + File.separator+ "Players", player.getUniqueId().toString()+".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        fileConfiguration.createSection("playerSettings");
        fileConfiguration.set("playerSettings.player_Name", player.getName());
        fileConfiguration.createSection("playerSettings.claimed_Chunks");
        fileConfiguration.createSection("playerSettings.main_Chunk");
        fileConfiguration.set("playerSettings.counter", 0);
        fileConfiguration.createSection("playerSettings.trusted_Players");
        fileConfiguration.createSection("playerSettings.registered_Players");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addChunks(Player player, Chunk chunk){
        File file = new File(plugin.getDataFolder() + File.separator+ "Players", player.getUniqueId().toString()+".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        List<String> existedChunks = (List<String>) fileConfiguration.getList("playerSettings.claimed_Chunks");

        List<String> newChunk = new ArrayList<String>();
        newChunk.add(chunk.toString());
        existedChunks.addAll(newChunk);

        fileConfiguration.set("playerSettings.counter", existedChunks);


        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getChunks(Player player){
        File file = new File(plugin.getDataFolder() + File.separator+ "Players", player.getUniqueId().toString()+".yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        return (List<String>) fileConfiguration.getList("playerSettings.claimed_Chunks");
    }


    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
