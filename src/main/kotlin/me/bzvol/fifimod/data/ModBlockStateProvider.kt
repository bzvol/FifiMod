package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.AquariumBlock
import me.bzvol.fifimod.block.FifhranyBlock
import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.client.renderer.RenderType
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.BlockStateProperties
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
        simpleBlock(ModBlocks.STEEL_BLOCK)
        simpleBlock(ModBlocks.FIFI_PLANKS)
        simpleBlock(ModBlocks.ASH_BLOCK)

        simpleBlock(
            ModBlocks.FIFI_SAPLING,
            models().withExistingParent("fifi_sapling", mcLoc("block/cross"))
                .texture("cross", ResourceLocation(FifiMod.MOD_ID, "block/fifi_sapling"))
                .renderType("cutout")
        )

        simpleBlock(
            ModBlocks.FIFI_LEAVES, *ConfiguredModel.builder()
                .modelFile(
                    models().withExistingParent("fifi_leaves", mcLoc("block/leaves"))
                        .texture("all", ResourceLocation(FifiMod.MOD_ID, "block/fifi_leaves"))
                        .renderType("cutout")
                )
                .weight(90)
                .nextModel()
                .modelFile(
                    models().withExistingParent("fifi_leaves_flower", mcLoc("block/leaves"))
                        .texture("all", ResourceLocation(FifiMod.MOD_ID, "block/fifi_leaves_flower"))
                        .renderType("cutout")
                )
                .weight(10)
                .build()
        )

        horizontalBlock(
            ModBlocks.CARVED_POTATO_BLOCK,
            ResourceLocation(FifiMod.MOD_ID, "block/potato_block"),
            ResourceLocation(FifiMod.MOD_ID, "block/carved_potato_block"),
            ResourceLocation(FifiMod.MOD_ID, "block/potato_block"),
        )

        horizontalBlock(ModBlocks.BISMUTH_BLOCK, cubeAll(ModBlocks.BISMUTH_BLOCK))

        horizontalBlock(
            ModBlocks.BURPBOX,
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_front"),
            ResourceLocation(FifiMod.MOD_ID, "block/burpbox_side")
        )

        horizontalBlock(
            ModBlocks.FIFI_SPAWNER, ModelFile.ExistingModelFile(
                ResourceLocation(FifiMod.MOD_ID, "block/fifi_spawner"),
                existingFileHelper
            )
        )

        axisBlock(ModBlocks.FIFI_LOG)
        axisBlock(ModBlocks.FIFI_WOOD)
        axisBlock(ModBlocks.STRIPPED_FIFI_LOG)
        axisBlock(ModBlocks.STRIPPED_FIFI_WOOD)

        val fifiPlanksTexture = ResourceLocation(FifiMod.MOD_ID, "block/fifi_planks")
        stairsBlock(ModBlocks.FIFI_STAIRS, fifiPlanksTexture)
        slabBlock(ModBlocks.FIFI_SLAB, fifiPlanksTexture, fifiPlanksTexture)
        fenceBlock(ModBlocks.FIFI_FENCE, fifiPlanksTexture)
        fenceGateBlock(ModBlocks.FIFI_FENCE_GATE, fifiPlanksTexture)
        models().fenceInventory("fifi_fence_inventory", fifiPlanksTexture)

        getVariantBuilder(ModBlocks.FIFHRANY)
            .forAllStates { state ->
                val age = state.getValue(FifhranyBlock.AGE)
                ConfiguredModel.builder()
                    .modelFile(
                        models()
                            .withExistingParent(
                                "fifhrany_stage$age",
                                mcLoc("block/crop")
                            )
                            .texture("crop", ResourceLocation(FifiMod.MOD_ID, "block/fifhrany_stage$age"))
                            .renderType("cutout")
                    )
                    .build()
            }

        getVariantBuilder(ModBlocks.AQUARIUM)
            .forAllStates { state ->
                val facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                val fill = state.getValue(AquariumBlock.FILL)
                ConfiguredModel.builder()
                    .modelFile(
                        ModelFile.UncheckedModelFile(
                            ResourceLocation(FifiMod.MOD_ID, "block/aquarium_${fill.modelName}"),
                        )
                    )
                    .rotationY(((facing.toYRot() + 180) % 360).toInt())
                    .build()
            }
    }

}