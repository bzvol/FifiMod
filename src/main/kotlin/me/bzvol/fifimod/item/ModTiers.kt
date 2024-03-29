package me.bzvol.fifimod.item

import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.ForgeTier

object ModTiers {
    val BRONZE: ForgeTier = ForgeTier(
        /* level = */ 2, /* uses = */ 200, /* speed = */ 5f, /* attackDamageBonus = */ 2f, /* enchantmentValue = */ 16,
        /* tag = */ BlockTags.NEEDS_IRON_TOOL
    ) { Ingredient.of(ModItems.BRONZE_INGOT) }

    val STEEL: ForgeTier = ForgeTier(
        2, 700, 7f, 3f, 10,
        BlockTags.NEEDS_IRON_TOOL
    ) { Ingredient.of(ModItems.STEEL) }

    val FIFI: ForgeTier = ForgeTier(
        5, 5077, 10f, 5f, 30,
        BlockTags.NEEDS_DIAMOND_TOOL
    ) { Ingredient.of(ModItems.FIFI) }

    val PIG_IRON: ForgeTier = ForgeTier(
        3, 1100, 8f, 3f, 10,
        BlockTags.NEEDS_DIAMOND_TOOL
    ) { Ingredient.of(ModItems.PIG_IRON_INGOT) }

    val UNIQUM: ForgeTier = ForgeTier(
        4, 600, 9f, 4f, 15,
        BlockTags.NEEDS_DIAMOND_TOOL
    ) { Ingredient.of(Items.NETHERITE_INGOT) }
}