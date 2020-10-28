package com.cyno.diablo.entities;

import com.cyno.diablo.Diablo;
import com.cyno.diablo.init.DiabloEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class DiabloFireParticleEntity extends AmbientEntity {
    static final double SPEED = 0.75D;
    LivingEntity shooter;
    int age;

    // would be private but EntityTypes needs to access it
    public DiabloFireParticleEntity(EntityType<? extends AmbientEntity> type, World world) {
        super(type, world);
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.age = 0;
    }

    // crashes if you don't do these even though they don't effect anything
    // also you need to bind this to the entity on FMLCommonSetupEvent
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return DiabloFireParticleEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 90.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15f)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.0)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 45.0);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        age++;
        if (age > 250 || this.getMotion() == Vector3d.ZERO){
            this.remove();
        }
    }

    // create a new one, set its shooter and send it in a direction
    public static void createNew(World world, LivingEntity shooter, float x, float z) {
        DiabloFireParticleEntity particle = new DiabloFireParticleEntity(DiabloEntityTypes.DIABLO_FIRE_PARTICLE.get(), world);

        particle.setPosition(shooter.getPosX(), shooter.getPosY(), shooter.getPosZ());

        // convert degress to xy on unit circle y->z
        Vector3d motion = (new Vector3d(x, 0, z)).normalize().scale(SPEED);
        particle.setMotion(motion);
        particle.shooter = shooter;

        world.addEntity(particle);

    }

    // when it hits an entity other than the shooter light them on fire and remove itself
    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (shooter == null) return;

        boolean isShooter = entityIn.getUniqueID() == shooter.getUniqueID();

        if (!world.isRemote() && entityIn instanceof LivingEntity && !isShooter){
            entityIn.setFire(10);
            this.remove();
        }
    }

    // when it runs into a block remove itself
    @Override
    protected void onInsideBlock(BlockState state) {
       if (!world.isRemote() && state.isSolid()){
           this.remove();
       }
    }
}
