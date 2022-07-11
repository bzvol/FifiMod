package me.bzvol.fifimod.block

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.util.ModCreativeModeTab
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.OreBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject
import kotlin.properties.ReadOnlyProperty

object ModBlocks {
    val REGISTRY: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, FifiMod.MOD_ID)

    val BISMUTH_ORE: Block by registerBlock("bismuth_ore", ModCreativeModeTab.FIFI_TAB) {
        OreBlock(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(5f, 3f)
                .requiresCorrectToolForDrops(),
            UniformInt.of(2, 7)
        )
    }

    val DEEPSLATE_BISMUTH_ORE: Block by registerBlock("deepslate_bismuth_ore", ModCreativeModeTab.FIFI_TAB) {
        OreBlock(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(5f, 3f)
                .requiresCorrectToolForDrops(),
            UniformInt.of(2, 7)
        )
    }

    val TIN_ORE by registerBlock("tin_ore", CreativeModeTab.TAB_BUILDING_BLOCKS) {
        OreBlock(
            BlockBehaviour.Properties
                .of(Material.STONE)
                .strength(3f)
                .requiresCorrectToolForDrops()
        )
    }

    val BISMUTH_BLOCK: Block by registerBlock("bismuth_block", ModCreativeModeTab.FIFI_TAB) {
        BismuthBlock(
            BlockBehaviour.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(3f)
                .sound(SoundType.METAL)
        )
    }

    val TIN_BLOCK by registerBlock("tin_block", CreativeModeTab.TAB_BUILDING_BLOCKS) {
        Block(
            BlockBehaviour.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(5f, 6f)
                .sound(SoundType.METAL)
        )
    }

    val BRONZE_BLOCK by registerBlock("bronze_block", CreativeModeTab.TAB_BUILDING_BLOCKS) {
        Block(
            BlockBehaviour.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(5f, 6f)
                .sound(SoundType.METAL)
        )
    }

    val STEEL_BLOCK by registerBlock("steel_block", CreativeModeTab.TAB_BUILDING_BLOCKS) {
        Block(
            BlockBehaviour.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(5f, 6f)
                .sound(SoundType.METAL)
        )
    }

    val BURPBOX: Block by registerBlock("burpbox", ModCreativeModeTab.FIFI_TAB) {
        BurpboxBlock(
            BlockBehaviour.Properties
                .of(Material.WOOD, MaterialColor.DIRT)
                .strength(2f, 6f)
        )
    }

    val FIFI_SPAWNER: Block by registerBlock("fifi_spawner", {
        FifiSpawnerBlock(
            BlockBehaviour.Properties
                .of(Material.STONE, MaterialColor.COLOR_PURPLE)
                .requiresCorrectToolForDrops()
                .lightLevel { 7 }
                .strength(5f, 1200f)
        )
    }) { BlockItem(it, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).rarity(Rarity.EPIC)) }

    val POTATO_BLOCK: Block by registerBlock("potato_block", ModCreativeModeTab.FIFI_TAB) {
        Block(
            BlockBehaviour.Properties
                .of(Material.VEGETABLE, MaterialColor.COLOR_BROWN)
                .strength(1f)
                .sound(SoundType.WOOD)
        )
    }

    val FIFI_LOG by registerBlock("fifi_log", ModCreativeModeTab.FIFI_TAB) {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LOG).noOcclusion())
    }
    val FIFI_WOOD by registerBlock("fifi_wood", ModCreativeModeTab.FIFI_TAB) {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_WOOD).noOcclusion())
    }
    val STRIPPED_FIFI_LOG by registerBlock("stripped_fifi_log", ModCreativeModeTab.FIFI_TAB) {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_LOG).noOcclusion())
    }
    val STRIPPED_FIFI_WOOD by registerBlock("stripped_fifi_wood", ModCreativeModeTab.FIFI_TAB) {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_WOOD).noOcclusion())
    }
    val FIFI_PLANKS by registerBlock("fifi_planks", ModCreativeModeTab.FIFI_TAB) {
        object : Block(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)) {
            override fun isFlammable(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Boolean = true

            override fun getFlammability(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Int = 20

            override fun getFireSpreadSpeed(
                state: BlockState,
                level: BlockGetter,
                pos: BlockPos,
                direction: Direction
            ): Int = 5
        }
    }


    private fun <T : Block> registerBlock(
        name: String, tab: CreativeModeTab, blockSupplier: () -> T
    ): ReadOnlyProperty<Any?, T> {
        val delegate = REGISTRY.registerObject(name, blockSupplier)

        registerBlockItem(name, tab, delegate)

        return delegate
    }

    private fun <T : Block> registerBlock(
        name: String, blockSupplier: () -> T, blockItemSupplier: (Block) -> BlockItem
    ): ReadOnlyProperty<Any?, T> {
        val delegate = REGISTRY.registerObject(name, blockSupplier)

        registerBlockItem(name, delegate, blockItemSupplier)

        return delegate
    }

    private fun <T : Block> registerBlockItem(
        name: String, tab: CreativeModeTab, blockDelegate: ReadOnlyProperty<Any?, T>
    ): ReadOnlyProperty<Any?, Item> = ModItems.REGISTRY.registerObject(name) {
        val block by blockDelegate
        BlockItem(block, Item.Properties().tab(tab))
    }

    private fun <T : Block> registerBlockItem(
        name: String, blockDelegate: ReadOnlyProperty<Any?, T>, blockItemSupplier: (Block) -> BlockItem
    ): ReadOnlyProperty<Any?, Item> = ModItems.REGISTRY.registerObject(name) {
        val block by blockDelegate
        blockItemSupplier(block)
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}