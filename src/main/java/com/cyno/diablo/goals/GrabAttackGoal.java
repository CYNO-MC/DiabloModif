package com.cyno.diablo.goals;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.entities.DiabloEntity;
import com.cyno.diablo.util.Debug;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

import java.util.UUID;

public class GrabAttackGoal extends Goal {
    final int RANGE_SQ = 4;  // can use this attack while within root x of the target
    final int DAMAGE_THRESHOLD = 40;  // releases after taking x/2 hearts of damage
    final int INTERVAL = 40;  // after releasing, must wait x ticks to grab again

    final DiabloEntity attacker;

    float startingHealth;
    LivingEntity target;
    UUID knockbackModifier;
    int cooldown = 0;

    public GrabAttackGoal(DiabloEntity attackerIn){
        this.attacker = attackerIn;
    }

    @Override
    public boolean shouldExecute() {
        // TODO: uncomment. was just to make testing easier
        boolean belowHalfHealth = true; // (attacker.getHealth() / attacker.getMaxHealth()) < 0.5D;
        if (!belowHalfHealth) return false;

        LivingEntity target = attacker.getAttackTarget();
        boolean hasTarget = target != null && target.isAlive();
        if (!hasTarget) return false;

        boolean cooldownPassed = attacker.ticksExisted >= cooldown;
        boolean withinRange = attacker.getDistanceSq(target) <= RANGE_SQ;

        return cooldownPassed && withinRange;
    }

    @Override
    public boolean shouldContinueExecuting() {
        boolean shouldRelease = attacker.getHealth() <= (startingHealth - DAMAGE_THRESHOLD);
        shouldRelease = shouldRelease || target == null || !target.isAlive();
        if (shouldRelease){
            // remove knockback resistance
            attacker.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(knockbackModifier);

            attacker.setAttackTarget(null);
            cooldown = attacker.ticksExisted + INTERVAL;
            attacker.isCurrentlyGrabbing = false;

            Debug.Log("target released");
        }

        Debug.Log(attacker.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue());
        return !shouldRelease;
    }

    @Override
    public void startExecuting() {
        Debug.Log("target grabbed");

        startingHealth = attacker.getHealth();
        target = attacker.getAttackTarget();
        attacker.isCurrentlyGrabbing = true;

        // give it knockback resistance so you can't hit it away. will be removed when you're released
        AttributeModifier mod = new AttributeModifier("diablo_grab_knockres", 1, AttributeModifier.Operation.ADDITION);
        knockbackModifier = mod.getID();
        attacker.getAttribute(Attributes.KNOCKBACK_RESISTANCE).applyPersistentModifier(mod);
    }

    @Override
    public void tick() {
        attacker.lookAt(EntityAnchorArgument.Type.EYES, target.getEyePosition(1));

        // if it knocked you back right before it grabbed you, run to you
        // without this you sometimes get stuck out of range and just die
        boolean isTooFar = attacker.getDistanceSq(target) > (RANGE_SQ+1);
        if (isTooFar){
            attacker.getNavigator().tryMoveToXYZ(target.getPosX(),target.getPosY(),target.getPosZ(), 3.0F);
        }

        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5, 9));
        target.attackEntityFrom(DamageSource.CRAMMING, 1F);  // actually only damages twice a second
    }
}
