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
        simpleItem(ModItems.AMETHYST_LIGHTER)
        simpleItem(ModItems.THE_FIFHER)
        simpleItem(ModItems.PETI_ITEM)
        simpleItem(ModItems.BEAN)
        simpleItem(ModItems.EFUM)
        simpleItem(ModItems.TIN_INGOT)
        simpleItem(ModItems.BRONZE_INGOT)
        simpleItem(ModItems.RAW_STEEL)
        simpleItem(ModItems.STEEL)
        simpleItem(ModItems.FIFI)
        simpleItem(ModItems.OXIDIZED_COPPER_INGOT)
        simpleItem(ModItems.PIG_IRON_INGOT)
        simpleItem(ModItems.FIFHRANY_SEEDS)

        simpleItem(ModItems.BRONZE_HELMET)
        simpleItem(ModItems.BRONZE_CHESTPLATE)
        simpleItem(ModItems.BRONZE_LEGGINGS)
        simpleItem(ModItems.BRONZE_BOOTS)
        simpleItem(ModItems.STEEL_HELMET)
        simpleItem(ModItems.STEEL_CHESTPLATE)
        simpleItem(ModItems.STEEL_LEGGINGS)
        simpleItem(ModItems.STEEL_BOOTS)
        simpleItem(ModItems.PIG_IRON_HELMET)
        simpleItem(ModItems.PIG_IRON_CHESTPLATE)
        simpleItem(ModItems.PIG_IRON_LEGGINGS)
        simpleItem(ModItems.PIG_IRON_BOOTS)

        handheldItem(ModItems.BRONZE_PICKAXE)
        handheldItem(ModItems.BRONZE_AXE)
        handheldItem(ModItems.BRONZE_SWORD)
        handheldItem(ModItems.BRONZE_SHOVEL)
        handheldItem(ModItems.BRONZE_HOE)
        handheldItem(ModItems.STEEL_PICKAXE)
        handheldItem(ModItems.STEEL_AXE)
        handheldItem(ModItems.STEEL_SWORD)
        handheldItem(ModItems.STEEL_SHOVEL)
        handheldItem(ModItems.STEEL_HOE)
        handheldItem(ModItems.PIG_IRON_SWORD)
        handheldItem(ModItems.FIFI_PICKAXE)
        handheldItem(ModItems.FIFI_AXE)
        handheldItem(ModItems.FIFI_SWORD)
        handheldItem(ModItems.FIFI_SHOVEL)
        handheldItem(ModItems.FIFI_HOE)

        withExistingParent(
            ModItems.FIFI_SPAWN_EGG.registryName?.path,
            mcLoc("item/template_spawn_egg")
        )

        blockItem(ModBlocks.BISMUTH_ORE)
        blockItem(ModBlocks.DEEPSLATE_BISMUTH_ORE)
        blockItem(ModBlocks.BISMUTH_BLOCK)
        blockItem(ModBlocks.BURPBOX)
        blockItem(ModBlocks.FIFI_SPAWNER)
        blockItem(ModBlocks.POTATO_BLOCK)
        blockItem(ModBlocks.TIN_ORE)
        blockItem(ModBlocks.TIN_BLOCK)
        blockItem(ModBlocks.BRONZE_BLOCK)
        blockItem(ModBlocks.STEEL_BLOCK)
        blockItem(ModBlocks.FIFI_LOG)
        blockItem(ModBlocks.STRIPPED_FIFI_LOG)
        blockItem(ModBlocks.FIFI_WOOD)
        blockItem(ModBlocks.STRIPPED_FIFI_WOOD)
        blockItem(ModBlocks.FIFI_LEAVES)
        blockItem(ModBlocks.FIFI_PLANKS)
        blockItem(ModBlocks.FIFI_STAIRS)
        blockItem(ModBlocks.FIFI_SLAB)
        blockItem(ModBlocks.FIFI_FENCE_GATE)
        getBuilder(ModBlocks.FIFI_FENCE.registryName?.path).parent(ModelFile.UncheckedModelFile(ResourceLocation(FifiMod.MOD_ID, "block/" + ModBlocks.FIFI_FENCE.registryName?.path + "_inventory")))
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