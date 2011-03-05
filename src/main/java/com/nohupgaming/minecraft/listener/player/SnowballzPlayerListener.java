package com.nohupgaming.minecraft.listener.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;

import com.nohupgaming.minecraft.Snowballz;
import com.nohupgaming.minecraft.util.SnowballzConstants;
import com.nohupgaming.minecraft.util.SnowballzUtil;

public class SnowballzPlayerListener extends PlayerListener 
{
    private final Snowballz _plugin;
    
    public SnowballzPlayerListener(final Snowballz plugin)
    {
        _plugin = plugin;
    }
    
    @Override
    public void onPlayerItem(PlayerItemEvent event) 
    {
        super.onPlayerItem(event);
        
        if (event.getItem().getType() == Material.SNOW_BALL &&
            SnowballzUtil.hasPermission(_plugin, event.getPlayer(), SnowballzConstants.PERMISSION_CHANGEBLOCK))
        {
            Block clicked = event.getPlayer().getTargetBlock(null, 
                _plugin.getConfiguration().getInt(Snowballz.SNOW_RANGE, 10));
            if (clicked != null && clicked.getType() != null)
            {
                Block above = clicked.getRelative(BlockFace.UP);
                if (above.getType().equals(Material.AIR))
                {
                    applyConversion(clicked);
                }
            }
        }
    }
    
    private Material getConversionType(Material m)
    {
        switch (m)
        {
            case DIRT:
            case GRASS:
            case SAND:
            case STONE:
            case COBBLESTONE:
            case BEDROCK:
            case BRICK:
            case CLAY:
            case COAL_ORE:
            case DIAMOND_ORE:
            case DIAMOND_BLOCK:
            case GLASS:
            case GLOWING_REDSTONE_ORE:
            case GOLD_BLOCK:
            case GOLD_ORE:
            case GRAVEL:
            case IRON_ORE:
            case IRON_BLOCK:
            case LAPIS_BLOCK:
            case LAPIS_ORE:
            case LEAVES:
            case LOG:
            case MOSSY_COBBLESTONE:
            case NETHERRACK:
            case OBSIDIAN:
            case PUMPKIN:
            case REDSTONE_ORE:
            case SANDSTONE:
            case SOIL:
            case SOUL_SAND:
            case SPONGE:
            case TNT:
            case WOOD:
            case WOOL:
                return Material.SNOW;
            case WATER:
            case STATIONARY_WATER:
                return Material.ICE;
            default:
                return m;
        }                        
    }
    
    private void applyConversion(Block b)
    {
        Material convertFrom = b.getType();
        Material convertTo = getConversionType(b.getType());
        
        if (convertTo != convertFrom)
        {
            if (convertTo.equals(Material.SNOW))
            {
                if (convertFrom.equals(Material.SOIL))
                {
                    b.setType(Material.DIRT);
                }
                b.getFace(BlockFace.UP).setType(Material.SNOW);
            }
            else if (convertTo.equals(Material.ICE) && _plugin
                .getConfiguration().getBoolean(Snowballz.SNOW_ICE, true))
            {
                if (b.getData() == 0x0)
                {
                    b.setType(Material.ICE);
                }
                else
                {
                    b.setType(Material.AIR);
                    Block below = b.getRelative(0, -1, 0);
                    applyConversion(below);
                }
            }
        }
        
    }
    
}
