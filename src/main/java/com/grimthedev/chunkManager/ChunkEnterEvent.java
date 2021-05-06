package com.grimthedev.chunkManager;


import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ChunkEnterEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private Chunk chunk;


    public ChunkEnterEvent(Player player,Chunk chunk){
       this.player = player;
       this.chunk = chunk;

    }

    public Chunk getChunk(){
        return chunk;
    }

    public Player getPlayer(){
        return player;
    }

    public void sendMessage(String message){
        player.sendMessage(message);
    }

    public void sendTitle(String title){
        player.sendTitle(title,"",1,20,3);
    }

    public void sendTitle(String title,String footer){
        player.sendTitle(title,footer,1,20,3);
    }

    public void playSound(Sound sound){
        player.playSound(player.getLocation(),sound,1f,1f);
    }

    public void playSound(Sound sound,float f1,float f2){
        player.playSound(player.getLocation(),sound,f1,f2);
    }


    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
}
