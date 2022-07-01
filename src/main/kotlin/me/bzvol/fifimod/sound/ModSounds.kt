package me.bzvol.fifimod.sound

import me.bzvol.fifimod.FifiMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import kotlin.properties.ReadOnlyProperty

object ModSounds {
    val REGISTRY: DeferredRegister<SoundEvent> = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FifiMod.MOD_ID)

    val BURP by registerSoundEvent("burp")

    val MUSIC_DISC_BIDIBODI by registerSoundEvent("music_disc_bidibodi")

    private fun registerSoundEvent(name: String): ReadOnlyProperty<Any?, SoundEvent> = REGISTRY.registerObject(name) {
        SoundEvent(ResourceLocation(FifiMod.MOD_ID, name))
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}