package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.util.ModUtil.registryName
import net.minecraft.client.renderer.block.model.ItemTransforms
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
        simpleItem(ModItems.SQUID_RINGS)
        simpleItem(ModItems.COOKED_SQUID_RINGS)
        simpleItem(ModItems.ASH)

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
        simpleItem(ModItems.FIFI_HOOD)
        simpleItem(ModItems.FIFI_SWEATER)
        simpleItem(ModItems.FIFI_PANTS)
        simpleItem(ModItems.FIFI_SLIPPERS)
        simpleItem(ModItems.FIFI_HAT)

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
        handheldItem(ModItems.PIG_IRON_PICKAXE)
        handheldItem(ModItems.PIG_IRON_AXE)
        handheldItem(ModItems.PIG_IRON_SWORD)
        handheldItem(ModItems.PIG_IRON_SHOVEL)
        handheldItem(ModItems.PIG_IRON_HOE)
        handheldItem(ModItems.FIFI_PICKAXE)
        handheldItem(ModItems.FIFI_AXE)
        handheldItem(ModItems.FIFI_SWORD)
        handheldItem(ModItems.FIFI_SHOVEL)
        handheldItem(ModItems.FIFI_HOE)
        handheldItem(ModItems.BETRAYER)
        handheldItem(ModItems.EXILED_SWORD)
        scaledHandheldItem(ModItems.POTATONIUM_SWORD, 1.2f)
        scaledHandheldItem(ModItems.STORMBRINGER, 1.2f)
        scaledHandheldItem(ModItems.SUSPENDER, 1.2f)

        for (i in 0..2)
            getBuilder("modern_bow_pulling_$i")
                .parent(ModelFile.UncheckedModelFile(ResourceLocation(FifiMod.MOD_ID, "item/modern_bow")))
                .texture("layer0", ResourceLocation(FifiMod.MOD_ID, "item/modern_bow_pulling_$i"))

        withExistingParent("modern_bow", ResourceLocation("item/generated"))
            .transforms()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
            .rotation(-80f, -280f, 40f).translation(-1f, -2f, 2.5f)
            .scale(.9f, .9f, .9f).end()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
            .rotation(-80f, 260f, -40f).translation(-1f, -2f, 2.5f)
            .scale(.9f, .9f, .9f).end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
            .rotation(0f, 90f, -25f).translation(1.13f, 3.2f, 1.13f)
            .scale(0.68f, 0.68f, 0.68f).end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            .rotation(0f, -90f, 25f).translation(1.13f, 3.2f, 1.13f)
            .scale(0.68f, 0.68f, 0.68f).end().end()
            .texture("layer0", ResourceLocation(FifiMod.MOD_ID, "item/modern_bow"))
            .override().predicate(ResourceLocation("pulling"), 1f).model(
                getExistingFile(ResourceLocation(FifiMod.MOD_ID, "item/modern_bow_pulling_0"))
            ).end()
            .override()
            .predicate(ResourceLocation("pulling"), 1f).predicate(ResourceLocation("pull"), 0.65f)
            .model(
                getExistingFile(ResourceLocation(FifiMod.MOD_ID, "item/modern_bow_pulling_1"))
            ).end()
            .override()
            .predicate(ResourceLocation("pulling"), 1f).predicate(ResourceLocation("pull"), 0.9f)
            .model(
                getExistingFile(ResourceLocation(FifiMod.MOD_ID, "item/modern_bow_pulling_2"))
            ).end()

        withExistingParent(
            ModItems.FIFI_SPAWN_EGG.registryName?.path,
            mcLoc("item/template_spawn_egg")
        )
        withExistingParent(
            ModItems.LITULY_SPAWN_EGG.registryName?.path,
            mcLoc("item/template_spawn_egg")
        )

        blockItem(ModBlocks.BISMUTH_ORE)
        blockItem(ModBlocks.DEEPSLATE_BISMUTH_ORE)
        blockItem(ModBlocks.BISMUTH_BLOCK)
        blockItem(ModBlocks.BURPBOX)
        blockItem(ModBlocks.FIFI_SPAWNER)
        blockItem(ModBlocks.POTATO_BLOCK)
        blockItem(ModBlocks.CARVED_POTATO_BLOCK)
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
        getBuilder("fifi_fence").parent(
            ModelFile.UncheckedModelFile(
                ResourceLocation(
                    FifiMod.MOD_ID,
                    "block/fifi_fence_inventory"
                )
            )
        )
        withExistingParent(
            "fifi_sapling",
            ResourceLocation("item/generated")
        ).texture(
            "layer0",
            ResourceLocation(FifiMod.MOD_ID, "block/fifi_sapling")
        )
        blockItem(ModBlocks.ASH_BLOCK)
        withExistingParent(
            "aquarium",
            ResourceLocation(FifiMod.MOD_ID, "block/aquarium_empty")
        )
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

    private fun scaledHandheldItem(item: Item, scale: Float): ItemModelBuilder =
        withExistingParent(
            item.registryName?.path,
            ResourceLocation("item/generated")
        ).transforms()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
            .rotation(0f, 90f, -25f)
            .translation(1.13f, 3.12f, 1.13f)
            .scale(scale).end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            .rotation(0f, -90f, 25f)
            .translation(1.13f, 3.12f, 1.13f)
            .scale(scale).end()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
            .rotation(0f, 90f, -55f)
            .translation(0f, 4f, .5f)
            .scale(scale).end()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
            .rotation(0f, -90f, 55f)
            .translation(0f, 4f, .5f)
            .scale(scale).end()
            .end()
            .texture(
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