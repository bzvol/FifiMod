package me.bzvol.fifimod.item

import net.minecraft.tags.BlockTags
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.ForgeTier

object ModTiers {
    val BRONZE: ForgeTier = ForgeTier(
        2, 200, 5f, 1.6f, 16,
        BlockTags.NEEDS_IRON_TOOL
    ) { Ingredient.of(ModItems.BRONZE_INGOT) }
}