package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.data.loot.ModGLMSerializers
import me.bzvol.fifimod.event.loot.SquidRingsFromSquidAdditionModifier
import me.bzvol.fifimod.item.ModItems
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition

class ModGlobalLootModifierProvider(generator: DataGenerator) : GlobalLootModifierProvider(generator, FifiMod.MOD_ID) {
    override fun start() {
        this.add(
            "squid_rings_from_squid_modifier",
            ModGLMSerializers.SQUID_RINGS_FROM_SQUID,
            SquidRingsFromSquidAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("entities/squid")).build()),
                ModItems.SQUID_RINGS
            )
        )
    }
}