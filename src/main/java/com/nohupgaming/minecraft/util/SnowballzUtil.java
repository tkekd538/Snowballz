package com.nohupgaming.minecraft.util;

import org.bukkit.entity.Player;

import com.nohupgaming.minecraft.Snowballz;

public class SnowballzUtil 
{

    public static boolean hasPermission(Snowballz p, Player pl, String path)
    {
        if (p.getPermissionHandler() != null && pl != null)
        {
            return p.getPermissionHandler().has(pl, path);
        }
        return true;
    }
    
}
