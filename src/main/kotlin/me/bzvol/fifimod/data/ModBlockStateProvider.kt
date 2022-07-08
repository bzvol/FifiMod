package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ConfiguredModel
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockStateProvider(generator: DataGenerator, private val existingFileHelper: ExistingFileHelper) :
    BlockStateProvider(generator, FifiMod.MOD_ID, existingFileHelper) {
    override fun registerStatesAndModels() {
        simpleBlock(ModBlocks.BISMUTH_ORE)
        simpleBlock(ModBlocks.DEEPSLATE_BISMUTH_ORE)
        simpleBlock(ModBlocks.POTATO_BLOCK)
        simpleBlock(ModBlocks.TIN_ORE)
        simpleBlock(ModBlocks.TIN_BLOCK)
        simpleBlock(ModBlocks.BRONZE_BLOCK)

        horizontalBlock(ModBlocks.BISMUTH_BLOCK, cubeAll(ModBlocks.BISMUTH_BLOCK))

        horizontalBlock(
            ModBlocks.BURPBOX,
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_front"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side")
        )

        horizontalBlock(ModBlocks.FIFI_SPAWNER, ModelFile.ExistingModelFile(
            ResourceLocation(FifiMod.MOD_ID, "block/fifi_spawner"),
            existingFileHelper
        ))
    }

}