package me.bzvol.fifimod.entity.client

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class FifiRenderer(renderManager: EntityRendererProvider.Context) :
    GeoEntityRenderer<FifiEntity>(renderManager, FifiModel()) {
    init {
        this.shadowRadius = 0.5f
    }

    override fun getTextureResource(instance: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/fifi/fifi.png")
}