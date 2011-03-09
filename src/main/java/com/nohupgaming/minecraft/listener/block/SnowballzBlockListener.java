package com.nohupgaming.minecraft.listener.block;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

import com.nohupgaming.minecraft.Snowballz;
import com.nohupgaming.minecraft.util.SnowballzConstants;
import com.nohupgaming.minecraft.util.SnowballzUtil;

public class SnowballzBlockListener extends BlockListener 
{
    private final Snowballz _plugin;
    
    public SnowballzBlockListener(Snowballz plugin)
    {
        _plugin = plugin;
    }
    
    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled() &&
            event.getBlock().getType().equals(Material.SNOW) &&
            event.getPlayer().getItemInHand().getType().equals(Material.AIR) &&
            SnowballzUtil.hasPermission(_plugin, event.getPlayer(), SnowballzConstants.PERMISSION_MAKESNOWBALL))
        {
            event.getPlayer().getWorld().dropItem(
                event.getBlock().getLocation(), 
                new ItemStack(Material.SNOW_BALL, 1));
        }
    }
    
    
}
