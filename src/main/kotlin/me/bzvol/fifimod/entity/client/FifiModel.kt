package me.bzvol.fifimod.entity.client

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class FifiModel : AnimatedGeoModel<FifiEntity>() {
    override fun getModelResource(`object`: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "geo/fifi.geo.json")

    override fun getTextureResource(`object`: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/entity/fifi/fifi.png")

    override fun getAnimationResource(animatable: FifiEntity): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "animations/fifi.animation.json")
}