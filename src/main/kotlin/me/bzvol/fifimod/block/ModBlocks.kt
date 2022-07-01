package me.bzvol.fifimod.block

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.tab.ModCreativeModeTab
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.OreBlock
import net.minecraft.world.level.block.state.BlockBehaviour
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

    val BISMUTH_BLOCK: Block by registerBlock("bismuth_block", ModCreativeModeTab.FIFI_TAB) {
        BismuthBlock(
            BlockBehaviour.Properties
                .of(Material.METAL)
                .strength(3f)
                .requiresCorrectToolForDrops()
        )
    }

    val BURPBOX: Block by registerBlock("burpbox", ModCreativeModeTab.FIFI_TAB) {
        BurpboxBlock(
            BlockBehaviour.Properties
                .of(Material.WOOD, MaterialColor.DIRT)
                .strength(2f, 6f)
        )
    }

    val FIFI_SPAWNER: Block by registerBlock("fifi_spawner", ModCreativeModeTab.FIFI_TAB) {
        FifiSpawnerBlock(
            BlockBehaviour.Properties
                .of(Material.STONE, MaterialColor.COLOR_PURPLE)
                .requiresCorrectToolForDrops()
                .lightLevel { 7 }
                .strength(5f, 1200f)
        )
    }

    private fun <T : Block> registerBlock(
        name: String, tab: CreativeModeTab, blockSupplier: () -> T
    ): ReadOnlyProperty<Any?, T> {
        val delegate = REGISTRY.registerObject(name, blockSupplier)

        registerBlockItem(name, tab, delegate)

        return delegate
    }

    private fun <T : Block> registerBlockItem(
        name: String, tab: CreativeModeTab, blockDelegate: ReadOnlyProperty<Any?, T>
    ): ReadOnlyProperty<Any?, Item> = ModItems.REGISTRY.registerObject(name) {
        val block by blockDelegate
        BlockItem(block, Item.Properties().tab(tab))
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}