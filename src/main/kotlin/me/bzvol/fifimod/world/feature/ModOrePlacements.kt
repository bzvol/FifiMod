package me.bzvol.fifimod.world.feature

import net.minecraft.core.Holder
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.*

object ModOrePlacements {
    val ORE_BISMUTH: Holder<PlacedFeature> = PlacementUtils.register(
        "ore_bismuth",
        ModConfiguredFeatures.BISMUTH_ORE, commonOrePlacement(
            10,
            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(120))
        )
    )

    private fun orePlacement(modifier1: PlacementModifier, modifier2: PlacementModifier): List<PlacementModifier> =
        listOf(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome())

    private fun commonOrePlacement(veinsPerChunk: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(CountPlacement.of(veinsPerChunk), heightRange)

    private fun rareOrePlacement(onceEvery: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(RarityFilter.onAverageOnceEvery(onceEvery), heightRange)
}