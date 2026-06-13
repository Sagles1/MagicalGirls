package com.magicalgirls.entities.ai;

import com.magicalgirls.entities.EntitySorakaCompanion;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;

public class EntityAIFollowMaster extends EntityAIBase {
    private final EntitySorakaCompanion companion;
    private final double followSpeed;
    private final float minDist;
    private final float maxDist;
    private int updateCounter = 0;

    public EntityAIFollowMaster(EntitySorakaCompanion companion, double speed, float minDist, float maxDist) {
        this.companion = companion;
        this.followSpeed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return this.companion.getOwner() != null && this.companion.getOwner().isEntityAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.shouldExecute();
    }

    @Override
    public void updateTask() {
        this.updateCounter++;
        
        if (this.updateCounter % 5 == 0) {
            this.updateCounter = 0;
            
            double distance = this.companion.getDistance(this.companion.getOwner());
            
            if (distance > this.maxDist) {
                this.companion.getNavigator().tryMoveToEntityLiving(this.companion.getOwner(), this.followSpeed);
            } else if (distance < this.minDist) {
                Vec3d walkAway = this.companion.getPositionVector().subtract(
                    this.companion.getOwner().getPositionVector()
                ).normalize().scale(0.5);
                this.companion.getNavigator().tryMoveToXYZ(
                    this.companion.posX + walkAway.x,
                    this.companion.posY,
                    this.companion.posZ + walkAway.z,
                    this.followSpeed * 0.5
                );
            }
        }
    }
}
