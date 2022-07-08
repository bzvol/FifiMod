package me.bzvol.fifimod.world.gen

import me.bzvol.fifimod.world.feature.ModOrePlacements
import net.minecraft.core.Holder
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.event.world.BiomeLoadingEvent

object ModOreGeneration {
    fun generateOres(event: BiomeLoadingEvent) {
        val base: MutableList<Holder<PlacedFeature>> =
            event.generation.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES)

        base.add(ModOrePlacements.ORE_BISMUTH)
        base.add(ModOrePlacements.ORE_TIN)
    }
}