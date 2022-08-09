package me.bzvol.fifimod.entity.client

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.LitulyEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class LitulyRenderer(renderManager: EntityRendererProvider.Context) :
    GeoEntityRenderer<LitulyEntity>(renderManager, LitulyModel()) {
    override fun getTextureLocation(instance: LitulyEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/lituly/lituly.png")
}