package com.magicalgirls.entities;

import com.magicalgirls.entities.ai.*;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntitySorakaCompanion extends EntityCreature {
    private static final DataParameter<String> OWNER_NAME = EntityDataManager.createKey(EntitySorakaCompanion.class, DataSerializers.STRING);
    private EntityPlayer owner;
    private int healCooldown = 0;
    private int slowCooldown = 0;

    public EntitySorakaCompanion(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.setupAI();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(OWNER_NAME, "");
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    protected void setupAI() {
        this.tasks.addTask(1, new EntityAIFollowMaster(this, 0.35D, 3.0F, 5.0F));
        this.tasks.addTask(2, new EntityAIHealMaster(this));
        this.tasks.addTask(3, new EntityAIAttackHostile(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAISlowSpell(this));
        this.tasks.addTask(5, new net.minecraft.entity.ai.EntityAIWander(this, 0.6D));
        this.tasks.addTask(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, net.minecraft.entity.monster.EntityMob.class, false));
    }

    public EntityPlayer getOwner() {
        return this.owner;
    }

    public void setOwner(EntityPlayer player) {
        this.owner = player;
        this.dataManager.set(OWNER_NAME, player.getName());
    }

    public int getHealCooldown() {
        return this.healCooldown;
    }

    public void setHealCooldown(int cooldown) {
        this.healCooldown = cooldown;
    }

    public int getSlowCooldown() {
        return this.slowCooldown;
    }

    public void setSlowCooldown(int cooldown) {
        this.slowCooldown = cooldown;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        
        if (this.owner == null && !this.dataManager.get(OWNER_NAME).isEmpty()) {
            this.owner = this.world.getPlayerEntityByName(this.dataManager.get(OWNER_NAME));
        }

        if (this.owner != null && this.getDistance(this.owner) > 64) {
            this.setDead();
        }

        if (this.healCooldown > 0) {
            this.healCooldown--;
        }
        if (this.slowCooldown > 0) {
            this.slowCooldown--;
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        if (this.owner != null) {
            compound.setString("OwnerName", this.owner.getName());
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        String ownerName = compound.getString("OwnerName");
        if (!ownerName.isEmpty()) {
            this.owner = this.world.getPlayerEntityByName(ownerName);
        }
    }

    @Override
    public boolean canDespawn() {
        return false;
    }
}
