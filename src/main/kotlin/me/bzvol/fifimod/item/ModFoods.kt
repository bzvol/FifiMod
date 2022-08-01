package me.bzvol.fifimod.item

import net.minecraft.world.food.FoodProperties

object ModFoods {
    val BOWL_OF_CCMPS: FoodProperties = FoodProperties.Builder()
        .nutrition(12)
        .saturationMod(0.8f)
        .build()

    val BEAN: FoodProperties = FoodProperties.Builder()
        .nutrition(5)
        .saturationMod(0.8f)
        .build()

    val SQUID_RINGS: FoodProperties = FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(0.2f)
        .build()

    val COOKED_SQUID_RINGS: FoodProperties = FoodProperties.Builder()
        .nutrition(4)
        .saturationMod(0.5f)
        .build()

}

