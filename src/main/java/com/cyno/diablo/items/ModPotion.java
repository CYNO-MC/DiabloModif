package com.cyno.diablo.items;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


// used for the drinkable arson potion
// allows you to make a drinkable potion item without automatically also making a splash, lingering and arrow variety
// basically a copy past of PotionItem

public class ModPotion extends ItemBase {
    private Supplier<EffectInstance> effect;
    public ModPotion(Supplier<EffectInstance> effectIn){
        this.effect = effectIn;  // needs to be a supplier because items are registered before effects
    }

    public EffectInstance getEffectInstance(){
        return this.effect.get();
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
        if (playerentity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
        }

        if (!worldIn.isRemote) {
            EffectInstance effectinstance = getEffectInstance();
            if (effectinstance.getPotion().isInstant()) {
                effectinstance.getPotion().affectEntity(playerentity, playerentity, entityLiving, effectinstance.getAmplifier(), 1.0D);
            } else {
                entityLiving.addPotionEffect(new EffectInstance(effectinstance));
            }
        }

        if (playerentity != null) {
            playerentity.addStat(Stats.ITEM_USED.get(this));
            if (!playerentity.abilities.isCreativeMode) {
                stack.shrink(1);
            }
        }

        // return bottle
        if (playerentity == null || !playerentity.abilities.isCreativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }


    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        addPotionTooltip(tooltip, 1.0F);
    }

    // slightly edited version of the one from PotionUtils
    @OnlyIn(Dist.CLIENT)
    public void addPotionTooltip(List<ITextComponent> lores, float durationFactor) {
        EffectInstance effectinstance = getEffectInstance();
        List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();

        IFormattableTextComponent iformattabletextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
        Effect effect = effectinstance.getPotion();
        Map<Attribute, AttributeModifier> map = effect.getAttributeModifierMap();
        if (!map.isEmpty()) {
            for(Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                AttributeModifier attributemodifier = entry.getValue();
                AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierAmount(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                list1.add(new Pair<>(entry.getKey(), attributemodifier1));
            }
        }

        if (effectinstance.getAmplifier() > 0) {
            iformattabletextcomponent = new TranslationTextComponent("potion.withAmplifier", iformattabletextcomponent, new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
        }

        if (effectinstance.getDuration() > 20) {
            iformattabletextcomponent = new TranslationTextComponent("potion.withDuration", iformattabletextcomponent, EffectUtils.getPotionDurationString(effectinstance, durationFactor));
        }

        lores.add(iformattabletextcomponent.mergeStyle(effect.getEffectType().getColor()));



        if (!list1.isEmpty()) {
            lores.add(StringTextComponent.EMPTY);
            lores.add((new TranslationTextComponent("potion.whenDrank")).mergeStyle(TextFormatting.DARK_PURPLE));

            for(Pair<Attribute, AttributeModifier> pair : list1) {
                AttributeModifier attributemodifier2 = pair.getSecond();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }

                if (d0 > 0.0D) {
                    lores.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent(pair.getFirst().func_233754_c_()))).mergeStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    lores.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent(pair.getFirst().func_233754_c_()))).mergeStyle(TextFormatting.RED));
                }
            }
        }

    }

    // add the glint
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }
}
