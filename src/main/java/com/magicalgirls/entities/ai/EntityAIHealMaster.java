package com.magicalgirls.entities.ai;

import com.magicalgirls.entities.EntitySorakaCompanion;
import com.magicalgirls.utils.ParticleEffects;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIHealMaster extends EntityAIBase {
    private final EntitySorakaCompanion companion;
    private int updateCounter = 0;

    public EntityAIHealMaster(EntitySorakaCompanion companion) {
        this.companion = companion;
        this.setMutexBits(0);
    }

    @Override
    public boolean shouldExecute() {
        if (this.companion.getOwner() == null || !this.companion.getOwner().isEntityAlive()) {
            return false;
        }
        
        float ownerHealth = this.companion.getOwner().getHealth();
        float maxHealth = this.companion.getOwner().getMaxHealth();
        
        return ownerHealth < (maxHealth * 0.9f);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    @Override
    public void updateTask() {
        this.updateCounter++;
        
        if (this.companion.getHealCooldown() <= 0 && this.updateCounter >= 40) {
            this.updateCounter = 0;
            
            if (this.companion.getOwner() != null) {
                // Soigner le joueur
                this.companion.getOwner().heal(4.0f);
                
                // Particules de soin
                ParticleEffects.spawnHealParticles(this.companion.world, this.companion.getOwner());
                
                // Cooldown
                this.companion.setHealCooldown(100);
            }
        }
    }
}
