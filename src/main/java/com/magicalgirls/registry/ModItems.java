package com.magicalgirls.registry;

import com.magicalgirls.items.ItemSoulGem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
    public static ItemSoulGem soulGem;

    public static void preInit() {
        soulGem = new ItemSoulGem();
        GameRegistry.register(soulGem);
    }

    public static void init() {
        // Item model registration if needed
    }
}
