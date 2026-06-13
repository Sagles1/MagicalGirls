package com.magicalgirls.registry;

import com.magicalgirls.MagicalGirls;
import com.magicalgirls.entities.EntitySorakaCompanion;
import com.magicalgirls.entities.render.RenderSorakaCompanion;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {
    private static int entityId = 0;

    public static void preInit() {
        entityId = 0;
        EntityRegistry.registerModEntity(
            EntitySorakaCompanion.class,
            "sorakaCompanion",
            entityId++,
            MagicalGirls.class,
            64,
            1,
            true
        );
    }

    public static void init() {
        clientInit();
    }

    @SideOnly(Side.CLIENT)
    public static void clientInit() {
        RenderingRegistry.registerEntityRenderingHandler(
            EntitySorakaCompanion.class,
            manager -> new RenderSorakaCompanion(manager)
        );
    }
}
