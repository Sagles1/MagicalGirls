package com.magicalgirls.utils;

import com.magicalgirls.entities.EntitySorakaCompanion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CompanionManager {
    
    /**
     * Récupère le compagnon Soraka du joueur s'il existe
     */
    public static EntitySorakaCompanion getCompanion(EntityPlayer player) {
        World world = player.world;
        
        for (EntitySorakaCompanion companion : world.getEntitiesWithinAABB(
            EntitySorakaCompanion.class,
            player.getEntityBoundingBox().expand(64.0D, 64.0D, 64.0D)
        )) {
            if (companion.getOwner() == player) {
                return companion;
            }
        }
        
        return null;
    }
    
    /**
     * Vérifie si le joueur a déjà un compagnon actif
     */
    public static boolean hasCompanion(EntityPlayer player) {
        return getCompanion(player) != null;
    }
    
    /**
     * Supprime le compagnon du joueur
     */
    public static void removeCompanion(EntityPlayer player) {
        EntitySorakaCompanion companion = getCompanion(player);
        if (companion != null) {
            companion.setDead();
        }
    }
}
