package com.grimthedev.examples;

import com.grimthedev.fileManager.PlayerFileCreator;
import com.grimthedev.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class addEXAMPLE implements CommandExecutor {

    private Main plugin;

    public addEXAMPLE(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player){
            PlayerFileCreator fileCreator = new PlayerFileCreator(plugin);
            Player player = (Player) sender;
            fileCreator.addChunks(player,player.getLocation().getChunk());

            /*for (String chunk : fileCreator.getChunks(player)){
                player.sendMessage(chunk.toString());
            }*/

        }else{
            System.out.println("Bu komudu burada kullanamazsın!");
        }

        return false;
    }
}
