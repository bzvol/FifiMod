@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package me.bzvol.fifimod.block.entity

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModBlockEntities {
    private val REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, FifiMod.MOD_ID)

    val FIFI_SPAWNER_ENTITY: BlockEntityType<*> by REGISTRY.registerObject("fifi_spawner_entity") {
        BlockEntityType.Builder.of(::FifiSpawnerBlockEntity, ModBlocks.FIFI_SPAWNER).build(null)
    }

    fun register(eventBus: IEventBus) = REGISTRY.register(eventBus)
}