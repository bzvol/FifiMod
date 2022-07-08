package me.bzvol.fifimod.item

import net.minecraft.tags.BlockTags
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.ForgeTier

object ModTiers {
    val BRONZE: ForgeTier = ForgeTier(
        /* level = */ 2, /* uses = */ 200, /* speed = */ 5f, /* attackDamageBonus = */ 1.6f, /* enchantmentValue = */ 16,
        /* tag = */ BlockTags.NEEDS_IRON_TOOL
    ) { Ingredient.of(ModItems.BRONZE_INGOT) }

    val STEEL: ForgeTier = ForgeTier(
        2, 700, 7f, 2.5f, 10,
        BlockTags.NEEDS_IRON_TOOL
    ) { Ingredient.of(ModItems.STEEL) }
}