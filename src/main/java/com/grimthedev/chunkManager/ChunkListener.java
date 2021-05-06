package com.grimthedev.chunkManager;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChunkListener implements Listener {

    @EventHandler
    public void playerChunkEvent(ChunkEnterEvent event){

        WorldBorderApi worldBorderApi = BorderAPI.getApi();

        Chunk c = event.getChunk();
        int locx = c.getX() * 16 + 8;
        int locz = c.getZ() * 16 +8;

        worldBorderApi.setBorder(event.getPlayer(),16.1,new Location(event.getPlayer().getWorld(),locx,0,locz));
        event.sendTitle("Yeni bir lokasyone girdin!","X: " + Integer.parseInt(String.valueOf(event.getChunk().getX() << 4)) + " Z: " + Integer.parseInt(String.valueOf(event.getChunk().getZ()<< 4)));

    }


}
