package me.bzvol.fifimod.world

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.world.gen.ModOreGeneration
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModWorldEvents {
    fun biomeLoadingEvent(event: BiomeLoadingEvent) {
        ModOreGeneration.generateOres(event)
    }
}