package com.magicalgirls.entities.ai;

import com.magicalgirls.entities.EntitySorakaCompanion;
import com.magicalgirls.utils.ParticleEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potions;

public class EntityAISlowSpell extends EntityAIBase {
    private final EntitySorakaCompanion companion;
    private EntityLivingBase target;
    private int updateCounter = 0;

    public EntityAISlowSpell(EntitySorakaCompanion companion) {
        this.companion = companion;
        this.setMutexBits(0);
    }

    @Override
    public boolean shouldExecute() {
        if (this.companion.getOwner() == null) {
            return false;
        }
        
        // Chercher les monstres proches qui attaquent le joueur
        EntityMob nearbyMob = this.companion.world.findNearestEntityWithinAABB(
            EntityMob.class,
            this.companion.getEntityBoundingBox().expand(20.0D, 10.0D, 20.0D),
            this.companion
        );
        
        if (nearbyMob != null && nearbyMob.getAttackTarget() == this.companion.getOwner()) {
            this.target = nearbyMob;
            return true;
        }
        
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.target != null && this.target.isEntityAlive();
    }

    @Override
    public void resetTask() {
        this.target = null;
    }

    @Override
    public void updateTask() {
        this.updateCounter++;
        
        if (this.companion.getSlowCooldown() <= 0 && this.updateCounter >= 60) {
            this.updateCounter = 0;
            
            if (this.target != null && this.companion.getDistance(this.target) < 20.0D) {
                // Appliquer le ralentissement
                this.target.addPotionEffect(new PotionEffect(Potions.SLOWNESS, 100, 1, false, false));
                
                // Particules du sort de ralentissement
                ParticleEffects.spawnSlowParticles(this.companion.world, this.target);
                
                // Cooldown
                this.companion.setSlowCooldown(120);
            }
        }
    }
}
