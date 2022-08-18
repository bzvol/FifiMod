package me.bzvol.fifimod.entity.client.armor

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.item.FifiArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.model.provider.GeoModelProvider

class FifiArmorModel : AnimatedGeoModel<FifiArmorItem>() {
    override fun getModelLocation(`object`: FifiArmorItem?): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "geo/fifi_armor.geo.json")

    override fun getTextureLocation(`object`: FifiArmorItem?): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "textures/models/armor/fifi_armor.png")

    override fun getAnimationFileLocation(animatable: FifiArmorItem?): ResourceLocation =
        ResourceLocation(FifiMod.MOD_ID, "animations/armor_animation.json")
}