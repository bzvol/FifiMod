package me.bzvol.fifimod.world.gen

import com.mojang.serialization.Codec
import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ModifiableBiomeInfo

data class EntityBiomeModifier(val biomes: HolderSet<Biome>, val feature: Holder<PlacedFeature>) : BiomeModifier {
    override fun modify(
        biome: Holder<Biome>,
        phase: BiomeModifier.Phase,
        builder: ModifiableBiomeInfo.BiomeInfo.Builder
    ) {
        if (phase == BiomeModifier.Phase.ADD && biomes.contains(biome)) {
            val base = builder.mobSpawnSettings.getSpawner(MobCategory.CREATURE)
            base.add(MobSpawnSettings.SpawnerData(ModEntityTypes.LITULY, 40, 5, 20))
        }
    }

    override fun codec(): Codec<out BiomeModifier> = ModBiomeModifiers.ENTITY_MODIFIER
}
