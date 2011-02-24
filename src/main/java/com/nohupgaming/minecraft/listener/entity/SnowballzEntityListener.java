package com.nohupgaming.minecraft.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

import com.nohupgaming.minecraft.Snowballz;

public class SnowballzEntityListener extends EntityListener 
{
    @SuppressWarnings("unused")
    private final Snowballz _plugin;
    
    public SnowballzEntityListener(final Snowballz plugin)
    {
        _plugin = plugin;
    }
    
    @Override
    public void onEntityDamage(EntityDamageEvent event) 
    {
        if (event instanceof EntityDamageByProjectileEvent)
        {
            EntityDamageByProjectileEvent edpe = (EntityDamageByProjectileEvent) event;

            if (edpe.getProjectile() instanceof Snowball)
            {
                Entity tgt = event.getEntity();
                if (tgt instanceof LivingEntity)
                {
                    event.setDamage(1);

                    if (tgt.getFireTicks() > 0)
                    {
                        tgt.setFireTicks(0);
                    }
                }
            }
        }
    }

}
