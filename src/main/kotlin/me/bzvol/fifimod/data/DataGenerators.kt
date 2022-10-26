package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val existingFileHelper = event.existingFileHelper

        generator.addProvider(true, ModRecipeProvider(generator))
        generator.addProvider(true, ModLootTableProvider(generator))
        generator.addProvider(true, ModGlobalLootModifierProvider(generator))

        generator.addProvider(true, ModItemModelProvider(generator, existingFileHelper))
        generator.addProvider(true, ModBlockStateProvider(generator, existingFileHelper))
    }
}