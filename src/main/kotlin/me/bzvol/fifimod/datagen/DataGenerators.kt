package me.bzvol.fifimod.datagen

import me.bzvol.fifimod.FifiMod
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val existingFileHelper = event.existingFileHelper

        generator.addProvider(ModRecipeProvider(generator))
        generator.addProvider(ModLootTableProvider(generator))

        generator.addProvider(ModItemModelProvider(generator, existingFileHelper))
        generator.addProvider(ModBlockStateProvider(generator, existingFileHelper))
    }
}