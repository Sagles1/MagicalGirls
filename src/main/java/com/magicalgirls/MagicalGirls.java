package com.magicalgirls;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import com.magicalgirls.registry.ModEntities;
import com.magicalgirls.registry.ModItems;
import org.apache.logging.log4j.Logger;

@Mod(modid = MagicalGirls.MODID, name = MagicalGirls.NAME, version = MagicalGirls.VERSION)
public class MagicalGirls {
    public static final String MODID = "magicalgirls";
    public static final String NAME = "Magical Girls";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Magical Girls mod - Pre-initialization");
        ModEntities.preInit();
        ModItems.preInit();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        logger.info("Magical Girls mod - Initialization");
        ModEntities.init();
        ModItems.init();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        logger.info("Magical Girls mod - Post-initialization");
    }
}
