package me.bzvol.fifimod.entity

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.projectile.Efum
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModEntityTypes {
    private val REGISTRY: DeferredRegister<EntityType<*>> =
        DeferredRegister.create(ForgeRegistries.ENTITIES, FifiMod.MOD_ID)

    val FIFI: EntityType<FifiEntity> by REGISTRY.registerObject("fifi") {
        EntityType.Builder.of(::FifiEntity, MobCategory.CREATURE)
            .sized(1.4f, 1.6f)
            .build(ResourceLocation(FifiMod.MOD_ID, "fifi").toString())
    }

    val EFUM: EntityType<Efum> by REGISTRY.registerObject("efum") {
        EntityType.Builder.of(::Efum, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .build(ResourceLocation(FifiMod.MOD_ID), "efum").toString())
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}