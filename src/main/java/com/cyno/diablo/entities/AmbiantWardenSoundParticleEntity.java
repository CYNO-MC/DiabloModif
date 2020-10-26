package com.cyno.diablo.entities;

import com.cyno.diablo.util.Debug;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class AmbiantWardenSoundParticleEntity extends AmbientEntity {

    private WardenEntity warden;
    public Vector3d position;

    public AmbiantWardenSoundParticleEntity(EntityType<? extends AmbientEntity> type, World p_i48570_2_) {
        super(type, p_i48570_2_);
        this.setNoGravity(true);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 100.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 10.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 30.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0);

    }
    @Override
    public void tick() {
        super.tick();
        if(!this.world.isRemote() && warden != null && position != null){
            Vector3d pos = new Vector3d(warden.getPosX(), warden.getPosYEye(), warden.getPosZ());
            Vector3d dir = new Vector3d((warden.getPosX() - this.getPosX()), (warden.getPosYEye() - this.getPosY()), (warden.getPosZ() - this.getPosZ())).normalize();
            this.lookAt(EntityAnchorArgument.Type.EYES, new Vector3d(warden.getPosX(), warden.getPosYEye(), warden.getPosZ()));
            this.addVelocity(dir.getX()* 0.1f, dir.getY()* 0.1f, dir.getZ()* 0.1f);
            if(this.getDistanceSq(pos) < 2f){
                this.warden.soundPosition = this.position;
                this.remove();
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }
    @Override
    public boolean canBeCollidedWith() {
            return false;
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
    }

    @Override
    protected void collideWithNearbyEntities() {
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
    }

    @Override
    public float getCollisionBorderSize() {
            return 0;
    }

    @Override
    protected void doBlockCollisions() {
    }


    public void setWarden(WardenEntity warden){
        this.warden = warden;
    }
    public void setSoundPosition(Vector3d position){
        this.position = position;
    }
}
