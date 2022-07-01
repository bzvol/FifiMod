package me.bzvol.fifimod.data.loot

import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.data.loot.BlockLoot
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.RegistryObject

class ModBlockLoot : BlockLoot() {
    override fun addTables() {
        dropSelf(ModBlocks.BISMUTH_BLOCK)
        dropSelf(ModBlocks.BURPBOX)
        dropSelf(ModBlocks.FIFI_SPAWNER)

        add(ModBlocks.BISMUTH_ORE) {
            createOreDrop(ModBlocks.BISMUTH_ORE, ModItems.BISMUTH)
        }
        add(ModBlocks.DEEPSLATE_BISMUTH_ORE) {
            createOreDrop(ModBlocks.DEEPSLATE_BISMUTH_ORE, ModItems.BISMUTH)
        }
    }

    override fun getKnownBlocks(): Iterable<Block> =
        ModBlocks.REGISTRY.entries.map(RegistryObject<Block>::get).asIterable()
}