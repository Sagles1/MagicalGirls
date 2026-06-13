package com.magicalgirls.items;

import com.magicalgirls.entities.EntitySorakaCompanion;
import com.magicalgirls.utils.CompanionManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSoulGem extends Item {
    public ItemSoulGem() {
        super();
        this.setUnlocalizedName("soulgem");
        this.setRegistryName("soulgem");
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {
            EntitySorakaCompanion existingCompanion = CompanionManager.getCompanion(player);

            if (existingCompanion != null) {
                // Téléporter Soraka au joueur
                existingCompanion.setLocationAndAngles(
                    player.posX,
                    player.posY,
                    player.posZ,
                    player.rotationYaw,
                    player.rotationPitch
                );
                player.sendMessage(new TextComponentString("§6Soraka téléportée!"));
            } else {
                // Créer une nouvelle Soraka
                EntitySorakaCompanion soraka = new EntitySorakaCompanion(world);
                soraka.setLocationAndAngles(
                    player.posX + 2,
                    player.posY,
                    player.posZ + 2,
                    player.rotationYaw,
                    player.rotationPitch
                );
                soraka.setOwner(player);
                world.spawnEntity(soraka);
                player.sendMessage(new TextComponentString("§6Soraka invoquée!"));
            }
        }

        return ActionResult.newResult(net.minecraft.util.EnumActionResult.SUCCESS, stack);
    }
}
