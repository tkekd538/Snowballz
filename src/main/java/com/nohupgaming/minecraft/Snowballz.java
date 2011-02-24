package com.nohupgaming.minecraft;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nohupgaming.minecraft.listener.block.SnowballzBlockListener;
import com.nohupgaming.minecraft.listener.entity.SnowballzEntityListener;
import com.nohupgaming.minecraft.listener.player.SnowballzPlayerListener;

public class Snowballz extends JavaPlugin 
{
    SnowballzPlayerListener _pl;
    SnowballzEntityListener _el;
    SnowballzBlockListener  _bl;
    
    public Snowballz()
    {
        _pl = new SnowballzPlayerListener(this);
        _el = new SnowballzEntityListener(this);
        _bl = new SnowballzBlockListener(this);
    }
    
    @Override
    public void onDisable() 
    {
        System.out.println("Snowballz has been disabled.");
    }

    @Override
    public void onEnable() 
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.PLAYER_ITEM, _pl, Priority.Normal, this);
        pm.registerEvent(Type.ENTITY_DAMAGED, _el, Priority.Normal, this);
        pm.registerEvent(Type.BLOCK_BREAK, _bl, Priority.Normal, this);
        System.out.println("Snowballz has been enabled.");
    }

}
