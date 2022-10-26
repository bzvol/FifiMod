package me.bzvol.fifimod.world.gen

import com.mojang.serialization.Codec
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ModifiableBiomeInfo

data class OreBiomeModifier(val biomes: HolderSet<Biome>, val feature: Holder<PlacedFeature>) : BiomeModifier {
    override fun modify(
        biome: Holder<Biome>,
        phase: BiomeModifier.Phase,
        builder: ModifiableBiomeInfo.BiomeInfo.Builder
    ) {
        if (phase == BiomeModifier.Phase.ADD && biomes.contains(biome)) {
            builder.generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature)
        }
    }

    override fun codec(): Codec<out BiomeModifier> = ModBiomeModifiers.ORE_MODIFIER
}