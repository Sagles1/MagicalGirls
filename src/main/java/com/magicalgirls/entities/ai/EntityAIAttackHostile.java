package com.magicalgirls.entities.ai;

import com.magicalgirls.entities.EntitySorakaCompanion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;

public class EntityAIAttackHostile extends EntityAIBase {
    private final EntitySorakaCompanion companion;
    private final double attackSpeed;
    private EntityLivingBase target;
    private int updateCounter = 0;

    public EntityAIAttackHostile(EntitySorakaCompanion companion, double speed, boolean pauseWhenMeleeAttacking) {
        this.companion = companion;
        this.attackSpeed = speed;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute() {
        if (this.companion.getOwner() == null) {
            return false;
        }
        
        // Chercher les monstres proches
        EntityMob nearbyMob = this.companion.world.findNearestEntityWithinAABB(
            EntityMob.class,
            this.companion.getEntityBoundingBox().expand(16.0D, 8.0D, 16.0D),
            this.companion
        );
        
        if (nearbyMob != null && this.companion.getDistance(nearbyMob) < 15.0D) {
            this.target = nearbyMob;
            return true;
        }
        
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (this.target == null || !this.target.isEntityAlive()) {
            return false;
        }
        
        return this.companion.getDistance(this.target) < 16.0D;
    }

    @Override
    public void startExecuting() {
        // Rien de particulier
    }

    @Override
    public void resetTask() {
        this.target = null;
    }

    @Override
    public void updateTask() {
        this.updateCounter++;
        
        if (this.target != null) {
            double distance = this.companion.getDistance(this.target);
            
            if (distance > 2.0D) {
                this.companion.getNavigator().tryMoveToEntityLiving(this.target, this.attackSpeed);
            } else {
                // Attaquer à distance
                if (this.updateCounter % 20 == 0) {
                    this.companion.attackEntityWithRangedAttack(this.target, 1.0f);
                }
            }
        }
    }
}
