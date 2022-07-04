package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    fun entityAttributeEvent(event: EntityAttributeCreationEvent) {
        event.put(ModEntityTypes.FIFI, FifiEntity.setAttributes())
    }
}