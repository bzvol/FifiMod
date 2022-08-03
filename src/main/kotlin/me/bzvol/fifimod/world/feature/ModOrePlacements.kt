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
            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(120))
        )
    )
    val ORE_TIN: Holder<PlacedFeature> = PlacementUtils.register(
        "ore_tin",
        ModConfiguredFeatures.TIN_ORE, commonOrePlacement(
            50,
            HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(100))
        )
    )

    val ORE_ASH: Holder<PlacedFeature> = PlacementUtils.register(
        "ore_ash",
        ModConfiguredFeatures.ASH_BLOCK, commonOrePlacement(
            17,
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31))
        )
    )

    private fun orePlacement(modifier1: PlacementModifier, modifier2: PlacementModifier): List<PlacementModifier> =
        listOf(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome())

    private fun commonOrePlacement(veinsPerChunk: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(CountPlacement.of(veinsPerChunk), heightRange)

    private fun rareOrePlacement(onceEvery: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(RarityFilter.onAverageOnceEvery(onceEvery), heightRange)
}