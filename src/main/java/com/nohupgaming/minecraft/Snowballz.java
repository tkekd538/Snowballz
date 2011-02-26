package com.nohupgaming.minecraft;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nohupgaming.minecraft.listener.block.SnowballzBlockListener;
import com.nohupgaming.minecraft.listener.entity.SnowballzEntityListener;
import com.nohupgaming.minecraft.listener.player.SnowballzPlayerListener;

public class Snowballz extends JavaPlugin 
{
    SnowballzPlayerListener _pl;
    SnowballzEntityListener _el;
    SnowballzBlockListener  _bl;
    
    public static final String SNOW_DAMAGE = "snowball.damage";
    public static final String SNOW_RANGE = "snowball.range";
    public static final String SNOW_FIRE = "snowball.ability.dousefire";
    public static final String SNOW_ICE = "snowball.ability.freezewater";
    
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
        if (!getDataFolder().exists()) 
        {
            buildConfiguration();
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Type.PLAYER_ITEM, _pl, Priority.Normal, this);
        pm.registerEvent(Type.ENTITY_DAMAGED, _el, Priority.Normal, this);
        pm.registerEvent(Type.BLOCK_BREAK, _bl, Priority.Normal, this);
        System.out.println("Snowballz has been enabled with the following options:");
        
        Configuration c = getConfiguration();
        System.out.println("    Snow damage: " + Integer.toString(c.getInt(SNOW_DAMAGE, -1)));
        System.out.println("    Snow range: " + Integer.toString(c.getInt(SNOW_RANGE, -1)));
        System.out.println("    Snow douses fire: " + Boolean.toString(c.getBoolean(SNOW_FIRE, true)));
        System.out.println("    Snow makes ice: " + Boolean.toString(c.getBoolean(SNOW_ICE, true)));
    }
    
    protected void buildConfiguration() 
    {
        Configuration c = getConfiguration();
        if (c != null)
        {
            c.setProperty(SNOW_DAMAGE, 1);
            c.setProperty(SNOW_RANGE, 10);        
            c.setProperty(SNOW_FIRE, true);
            c.setProperty(SNOW_ICE, true);
    
            if (!c.save())
            {
                getServer().getLogger().warning("Unable to persist configuration files, changes will not be saved.");
            }
        }
    }


}
