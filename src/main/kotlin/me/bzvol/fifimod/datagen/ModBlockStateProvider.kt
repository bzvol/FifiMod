package me.bzvol.fifimod.datagen

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockStateProvider(generator: DataGenerator, existingFileHelper: ExistingFileHelper) :
    BlockStateProvider(generator, FifiMod.MOD_ID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlock(ModBlocks.BISMUTH_ORE)
        simpleBlock(ModBlocks.DEEPSLATE_BISMUTH_ORE)
        simpleBlock(ModBlocks.BISMUTH_BLOCK)

        horizontalBlock(
            ModBlocks.BURPBOX,
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_front"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side"),
        )
    }

}