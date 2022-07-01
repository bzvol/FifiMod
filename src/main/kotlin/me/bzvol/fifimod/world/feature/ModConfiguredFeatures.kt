package me.bzvol.fifimod.world.feature

import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.core.Holder
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration

object ModConfiguredFeatures {
    val ORE_BISMUTH_TARGET_LIST: List<OreConfiguration.TargetBlockState> = listOf(
        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BISMUTH_ORE.defaultBlockState()),
        OreConfiguration.target(
            OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
            ModBlocks.DEEPSLATE_BISMUTH_ORE.defaultBlockState()
        )
    )

    val BISMUTH_ORE: Holder<ConfiguredFeature<OreConfiguration, *>> = FeatureUtils.register(
        "ore_bismuth",
        Feature.ORE, OreConfiguration(ORE_BISMUTH_TARGET_LIST, 3, 0.3f)
    )
}