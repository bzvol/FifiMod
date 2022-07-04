package me.bzvol.fifimod.entity.client

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class FifiModel : AnimatedGeoModel<FifiEntity>() {
    override fun getModelLocation(`object`: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "geo/fifi.geo.json")

    override fun getTextureLocation(`object`: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/fifi/fifi.png")

    override fun getAnimationFileLocation(animatable: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "animations/fifi.animation.json")
}