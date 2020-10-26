package com.cyno.diablo.util;

import com.cyno.diablo.entities.WardenEntity;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerEventBusSubscriber {
    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event){
        WardenEntity.instance = null;
    }
}

