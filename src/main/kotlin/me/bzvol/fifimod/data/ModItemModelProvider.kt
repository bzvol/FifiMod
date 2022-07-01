package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.model.generators.ItemModelBuilder
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper


class ModItemModelProvider(generator: DataGenerator, existingFileHelper: ExistingFileHelper) :
    ItemModelProvider(generator, FifiMod.MOD_ID, existingFileHelper) {
    override fun registerModels() {
        simpleItem(ModItems.BISMUTH)
        simpleItem(ModItems.POPPY_SEEDS)
        simpleItem(ModItems.COTTAGE_CHEESE)
        simpleItem(ModItems.BOWL_OF_CCMPS)
        simpleItem(ModItems.MUSIC_DISC_BIDIBODI)

        blockItem(ModBlocks.BISMUTH_ORE)
        blockItem(ModBlocks.DEEPSLATE_BISMUTH_ORE)
        blockItem(ModBlocks.BISMUTH_BLOCK)
        blockItem(ModBlocks.BURPBOX)
        blockItem(ModBlocks.FIFI_SPAWNER)
    }

    private fun simpleItem(item: Item): ItemModelBuilder =
        withExistingParent(
            item.registryName?.path,
            ResourceLocation("item/generated")
        ).texture(
            "layer0",
            ResourceLocation(FifiMod.MOD_ID, "item/" + item.registryName?.path)
        )

    private fun handheldItem(item: Item): ItemModelBuilder =
        withExistingParent(
            item.registryName?.path,
            ResourceLocation("item/handheld")
        ).texture(
            "layer0",
            ResourceLocation(FifiMod.MOD_ID, "item/" + item.registryName?.path)
        )

    private fun blockItem(block: Block): ItemModelBuilder =
        getBuilder(block.asItem().registryName?.path)
            .parent(
                ModelFile.UncheckedModelFile(
                    ResourceLocation(FifiMod.MOD_ID, "block/" + block.registryName?.path)
                )
            )
}