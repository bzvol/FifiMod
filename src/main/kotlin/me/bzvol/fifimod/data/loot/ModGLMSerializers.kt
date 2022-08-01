package me.bzvol.fifimod.data.loot

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.event.loot.SquidRingsFromSquidAdditionModifier
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModGLMSerializers {
    private val REGISTRY = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, FifiMod.MOD_ID)

    val SQUID_RINGS_FROM_SQUID by REGISTRY.registerObject("squid_ring_from_squid") {
        SquidRingsFromSquidAdditionModifier.Serializer()
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}