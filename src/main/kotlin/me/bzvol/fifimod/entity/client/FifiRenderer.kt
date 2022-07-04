package me.bzvol.fifimod.entity.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class FifiRenderer(renderManager: EntityRendererProvider.Context) :
    GeoEntityRenderer<FifiEntity>(renderManager, FifiModel()) {
    init {
        this.shadowRadius = 0.5f
    }

    override fun getTextureLocation(instance: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/fifi/fifi.png")
}