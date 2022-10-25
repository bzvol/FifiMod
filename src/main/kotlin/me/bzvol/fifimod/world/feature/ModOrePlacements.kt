@file:Suppress("UNCHECKED_CAST")

package me.bzvol.fifimod.world.feature

import me.bzvol.fifimod.FifiMod
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject

object ModOrePlacements {
    val REGISTRY: DeferredRegister<PlacedFeature> =
        DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, FifiMod.MOD_ID)

    val ORE_BISMUTH by REGISTRY.registerObject("ore_bismuth") {
        PlacedFeature(
            ModConfiguredFeatures.BISMUTH_ORE as Holder<ConfiguredFeature<*, *>>, commonOrePlacement(
                7,
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(120))
            )
        )
    }
    val ORE_TIN by REGISTRY.registerObject("ore_tin") {
        PlacedFeature(
            ModConfiguredFeatures.TIN_ORE as Holder<ConfiguredFeature<*, *>>, commonOrePlacement(
                25,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(100))
            )
        )
    }

    val ORE_ASH by REGISTRY.registerObject("ore_ash")
    {
        PlacedFeature(
            ModConfiguredFeatures.ASH_BLOCK as Holder<ConfiguredFeature<*, *>>, commonOrePlacement(
                100,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31))
            )
        )
    }

    private fun orePlacement(modifier1: PlacementModifier, modifier2: PlacementModifier): List<PlacementModifier> =
        listOf(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome())

    private fun commonOrePlacement(veinsPerChunk: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(CountPlacement.of(veinsPerChunk), heightRange)

    private fun rareOrePlacement(onceEvery: Int, heightRange: PlacementModifier): List<PlacementModifier> =
        orePlacement(RarityFilter.onAverageOnceEvery(onceEvery), heightRange)
}