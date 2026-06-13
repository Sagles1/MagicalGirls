package com.magicalgirls.entities.render;

import com.magicalgirls.MagicalGirls;
import com.magicalgirls.entities.EntitySorakaCompanion;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSorakaCompanion extends RenderBiped<EntitySorakaCompanion> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MagicalGirls.MODID + ":textures/entity/soraka.png");

    public RenderSorakaCompanion(RenderManager renderManager) {
        super(renderManager, new ModelPlayer(0.0f, false), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySorakaCompanion entity) {
        return TEXTURE;
    }
}
