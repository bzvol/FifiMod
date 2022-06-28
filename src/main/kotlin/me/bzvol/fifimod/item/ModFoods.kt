package me.bzvol.fifimod.item

import net.minecraft.world.food.FoodProperties

object ModFoods {
    val BOWL_OF_CCMPS: FoodProperties = (FoodProperties.Builder())
        .nutrition(12)
        .saturationMod(0.8f)
        .build()
}