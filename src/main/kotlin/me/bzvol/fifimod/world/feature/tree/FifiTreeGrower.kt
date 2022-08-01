package me.bzvol.fifimod.world.feature.tree

import me.bzvol.fifimod.world.feature.ModConfiguredFeatures
import net.minecraft.core.Holder
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import java.util.*

class FifiTreeGrower : AbstractMegaTreeGrower() {
    override fun getConfiguredFeature(pRandom: Random, pLargeHive: Boolean): Holder<out ConfiguredFeature<*, *>>? =
        null

    override fun getConfiguredMegaFeature(pRandom: Random): Holder<out ConfiguredFeature<*, *>> =
        ModConfiguredFeatures.FIFI_TREE
}