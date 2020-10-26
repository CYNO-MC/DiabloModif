package com.cyno.diablo.util;

import com.cyno.diablo.entities.WardenEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SoundHandler {

    @SubscribeEvent
    public static void onEntitySound(EntityEvent event) // get the entities that make noises, calculated with the speed of the entity, it is not possible to get the emitted sound from entities for some reason
    {
       if(WardenEntity.instance != null && event.getEntity() != null && !WardenEntity.instance.world.isRemote() && ((event.getEntity() instanceof PlayerEntity && Minecraft.getInstance().player != null && !Minecraft.getInstance().player.isCreative()) || event.getEntity() instanceof VillagerEntity)){

            float disToWarden = WardenEntity.instance.getDistance(event.getEntity()); // distance to the warden
            if(disToWarden < 20 && (!event.getEntity().isSilent() && event.getEntity().getMotion().length() > 0.15f))
            {
                WardenEntity.instance.lastHeardPos = new Vector3d(event.getEntity().getPosition().getX(),  event.getEntity().getPosition().getY() + 1, event.getEntity().getPosition().getZ());
                WardenEntity.instance.canHear = true;
            }
        }

    }

    @SubscribeEvent
    public static void onProjectileHitSound(ProjectileImpactEvent event){ // get a projectile that landed
        if(WardenEntity.instance != null && event.getEntity() != null && !WardenEntity.instance.world.isRemote()){
            float disToWarden = WardenEntity.instance.getDistance(event.getEntity());
            if(disToWarden < 20)
            {
                WardenEntity.instance.lastHeardPos = new Vector3d(event.getEntity().getPosition().getX(), event.getEntity().getPosition().getY(), event.getEntity().getPosition().getZ());
                WardenEntity.instance.currentParticlesDelay = WardenEntity.instance.maxParticlesDelay;
                WardenEntity.instance.canHear = true;
            }
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent event){ // get an explosion that occurs

        if(WardenEntity.instance != null && !WardenEntity.instance.world.isRemote()){
            float disToWarden = (float) WardenEntity.instance.getDistanceSq(event.getExplosion().getPosition());
            if(disToWarden < 200)
            {
                WardenEntity.instance.lastHeardPos =event.getExplosion().getPosition();
                WardenEntity.instance.currentParticlesDelay = WardenEntity.instance.maxParticlesDelay;
                WardenEntity.instance.canHear = true;
            }
        }

    }

}
