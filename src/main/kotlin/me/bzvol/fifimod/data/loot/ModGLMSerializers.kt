package me.bzvol.fifimod.data.loot

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.event.loot.OneItemAdditionModifier
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModGLMSerializers {
    private val REGISTRY = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, FifiMod.MOD_ID)

    val ONE_ITEM_MODIFIER by REGISTRY.registerObject("one_item_modifier") {
        OneItemAdditionModifier.Serializer()
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}