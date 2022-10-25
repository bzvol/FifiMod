package me.bzvol.fifimod.world.feature.tree

import me.bzvol.fifimod.world.feature.ModConfiguredFeatures
import net.minecraft.core.Holder
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import java.util.*

class FifiTreeGrower : AbstractMegaTreeGrower() {
    override fun getConfiguredFeature(pRandom: RandomSource, pLargeHive: Boolean): Holder<out ConfiguredFeature<*, *>>? =
        null

    override fun getConfiguredMegaFeature(pRandom: RandomSource): Holder<out ConfiguredFeature<*, *>> =
        ModConfiguredFeatures.FIFI_TREE
}