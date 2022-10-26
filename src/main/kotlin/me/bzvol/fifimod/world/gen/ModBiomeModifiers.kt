package me.bzvol.fifimod.world.gen

import me.bzvol.fifimod.FifiMod
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ModBiomeModifiers {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, FifiMod.MOD_ID)



    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}