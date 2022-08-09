package me.bzvol.fifimod.world.gen

import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraftforge.event.world.BiomeLoadingEvent

object ModEntityGeneration {
    fun addEntities(event: BiomeLoadingEvent) {
        if (event.name.toString() == Biomes.JUNGLE.location().toString()) {
            val base = event.spawns.getSpawner(MobCategory.CREATURE)
            base.add(MobSpawnSettings.SpawnerData(ModEntityTypes.LITULY, 40, 5, 20))
        }
    }
}