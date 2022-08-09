package me.bzvol.fifimod.entity.client

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.LitulyEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class LitulyModel : AnimatedGeoModel<LitulyEntity>() {
    override fun getModelLocation(`object`: LitulyEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "geo/lituly.geo.json")

    override fun getTextureLocation(`object`: LitulyEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/lituly/lituly.png")

    override fun getAnimationFileLocation(animatable: LitulyEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "animations/lituly.animation.json")
}