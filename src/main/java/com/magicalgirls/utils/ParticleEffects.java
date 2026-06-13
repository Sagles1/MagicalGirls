package com.magicalgirls.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleEffects {
    
    /**
     * Particules de soin (SPELL_INSTANT - vert/blanc)
     */
    public static void spawnHealParticles(World world, EntityLivingBase entity) {
        if (world.isRemote) {
            return;
        }
        
        double x = entity.posX;
        double y = entity.posY + entity.height / 2;
        double z = entity.posZ;
        
        for (int i = 0; i < 10; i++) {
            double offsetX = (Math.random() - 0.5) * 1.5;
            double offsetY = Math.random() * 1.5;
            double offsetZ = (Math.random() - 0.5) * 1.5;
            
            world.spawnParticle(
                EnumParticleTypes.SPELL_INSTANT,
                x + offsetX,
                y + offsetY,
                z + offsetZ,
                0, 0.1, 0
            );
        }
    }
    
    /**
     * Particules de ralentissement (SPELL_MOB - bleu)
     */
    public static void spawnSlowParticles(World world, EntityLivingBase entity) {
        if (world.isRemote) {
            return;
        }
        
        double x = entity.posX;
        double y = entity.posY + entity.height / 2;
        double z = entity.posZ;
        
        for (int i = 0; i < 15; i++) {
            double offsetX = (Math.random() - 0.5) * 2.0;
            double offsetY = (Math.random() - 0.5) * 2.0;
            double offsetZ = (Math.random() - 0.5) * 2.0;
            
            world.spawnParticle(
                EnumParticleTypes.SPELL_MOB,
                x + offsetX,
                y + offsetY,
                z + offsetZ,
                0, 0.05, 0
            );
        }
    }
    
    /**
     * Particules de projectile magique
     */
    public static void spawnMagicProjectileParticles(World world, double x, double y, double z) {
        if (world.isRemote) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            double offsetX = (Math.random() - 0.5) * 0.5;
            double offsetY = (Math.random() - 0.5) * 0.5;
            double offsetZ = (Math.random() - 0.5) * 0.5;
            
            world.spawnParticle(
                EnumParticleTypes.SPELL,
                x + offsetX,
                y + offsetY,
                z + offsetZ,
                (Math.random() - 0.5) * 0.2,
                (Math.random() - 0.5) * 0.2,
                (Math.random() - 0.5) * 0.2
            );
        }
    }
}
