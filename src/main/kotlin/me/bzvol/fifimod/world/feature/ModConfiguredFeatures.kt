package me.bzvol.fifimod.world.feature

import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.core.Holder
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer
import java.util.*

object ModConfiguredFeatures {
    private val ORE_BISMUTH_TARGET_LIST: List<OreConfiguration.TargetBlockState> = listOf(
        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BISMUTH_ORE.defaultBlockState()),
        OreConfiguration.target(
            OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
            ModBlocks.DEEPSLATE_BISMUTH_ORE.defaultBlockState()
        )
    )
    val ORE_TIN_TARGET_LIST: List<OreConfiguration.TargetBlockState> =
        listOf(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.defaultBlockState()))

    val BISMUTH_ORE: Holder<ConfiguredFeature<OreConfiguration, *>> =
        FeatureUtils.register("ore_bismuth", Feature.ORE, OreConfiguration(ORE_BISMUTH_TARGET_LIST, 5))
    val TIN_ORE: Holder<ConfiguredFeature<OreConfiguration, *>> =
        FeatureUtils.register("ore_tin", Feature.ORE, OreConfiguration(ORE_TIN_TARGET_LIST, 9))

    val ASH_BLOCK: Holder<ConfiguredFeature<OreConfiguration, *>> =
        FeatureUtils.register(
            "ore_ash",
            Feature.ORE,
            OreConfiguration(OreFeatures.NETHERRACK, ModBlocks.ASH_BLOCK.defaultBlockState(), 25)
        )

    val FIFI_TREE: Holder<ConfiguredFeature<TreeConfiguration, *>> =
        FeatureUtils.register(
            "fifi_tree", Feature.TREE, TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.FIFI_LOG),
                GiantTrunkPlacer(14, 6, 6),
                BlockStateProvider.simple(ModBlocks.FIFI_LEAVES),
                SpruceFoliagePlacer(UniformInt.of(4, 8), UniformInt.of(2, 4), UniformInt.of(2, 3)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            ).dirt(BlockStateProvider.simple(Blocks.SAND)).ignoreVines().build()
        )
}