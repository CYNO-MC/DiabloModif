package com.cyno.diablo.init;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.effects.ArsonEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Diablo.MOD_ID);

    // liquidColorIn isn't used because I use a custom item for the potion
    public static final RegistryObject<Effect> ARSON_EFFECT = EFFECTS.register("arson", () -> new ArsonEffect(EffectType.HARMFUL, 0xff8400));
}