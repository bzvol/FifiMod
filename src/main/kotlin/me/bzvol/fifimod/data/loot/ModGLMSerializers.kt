package me.bzvol.fifimod.data.loot

import com.mojang.serialization.Codec
import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.event.loot.MultipleOrItemAdditionModifier
import me.bzvol.fifimod.event.loot.OneItemAdditionModifier
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModGLMSerializers {
    private val REGISTRY: DeferredRegister<Codec<out IGlobalLootModifier>> = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FifiMod.MOD_ID)

    val ONE_ITEM_MODIFIER by REGISTRY.registerObject("one_item_modifier") {
        OneItemAdditionModifier.CODEC.get()
    }

    val MULTIPLE_OR_ITEM_MODIFIER by REGISTRY.registerObject("multiple_or_item_modifier") {
        MultipleOrItemAdditionModifier.CODEC.get()
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}