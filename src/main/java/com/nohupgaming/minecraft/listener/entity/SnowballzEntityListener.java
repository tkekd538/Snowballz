package com.nohupgaming.minecraft.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

import com.nohupgaming.minecraft.Snowballz;
import com.nohupgaming.minecraft.util.SnowballzConstants;
import com.nohupgaming.minecraft.util.SnowballzUtil;

public class SnowballzEntityListener extends EntityListener 
{
    private final Snowballz _plugin;
    
    public SnowballzEntityListener(final Snowballz plugin)
    {
        _plugin = plugin;
    }
    
    @Override
    public void onEntityDamage(EntityDamageEvent event) 
    {        
        if (!event.isCancelled() && event instanceof EntityDamageByProjectileEvent)
        {
            EntityDamageByProjectileEvent edpe = (EntityDamageByProjectileEvent) event;

            if (edpe.getProjectile() instanceof Snowball)
            {
                Entity tgt = event.getEntity();
                if (tgt instanceof LivingEntity)
                {
                    Player pl = edpe.getDamager() instanceof Player ? (Player) edpe.getDamager() : null;
                    if (SnowballzUtil.hasPermission(_plugin, pl, SnowballzConstants.PERMISSION_SNOWDAMAGE))
                    {
                        event.setDamage(_plugin.getConfiguration().getInt(
                            Snowballz.SNOW_DAMAGE, 1));
                    }

                    if (tgt.getFireTicks() > 0 &&
                        _plugin.getConfiguration().getBoolean(
                            Snowballz.SNOW_FIRE, true) &&
                        SnowballzUtil.hasPermission(_plugin, pl, SnowballzConstants.PERMISSION_PUTOUTFIRE))
                    {
                        tgt.setFireTicks(0);
                    }
                }
            }
        }
    }

}
