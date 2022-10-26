package me.bzvol.fifimod.world.gen

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import me.bzvol.fifimod.FifiMod
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModBiomeModifiers {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, FifiMod.MOD_ID)

    val ORE_MODIFIER: Codec<OreBiomeModifier> by REGISTRY.registerObject("ore") {
        RecordCodecBuilder.create { builder ->
            builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter { it.biomes  },
                PlacedFeature.CODEC.fieldOf("feature").forGetter { it.feature }
            ).apply(builder, ::OreBiomeModifier)
        }
    }

    val ENTITY_MODIFIER: Codec<EntityBiomeModifier> by REGISTRY.registerObject("entity") {
        RecordCodecBuilder.create { builder ->
            builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter { it.biomes  },
                MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawner").forGetter { it.spawner }
            ).apply(builder, ::EntityBiomeModifier)
        }
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}