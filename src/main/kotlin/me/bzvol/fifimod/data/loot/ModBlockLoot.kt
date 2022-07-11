package me.bzvol.fifimod.data.loot

import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.loot.BlockLoot
import net.minecraft.util.valueproviders.UniformFloat
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.MatchTool
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraftforge.registries.RegistryObject

class ModBlockLoot : BlockLoot() {
    override fun addTables() {
        dropSelf(ModBlocks.BISMUTH_BLOCK)
        dropSelf(ModBlocks.BURPBOX)
        dropSelf(ModBlocks.FIFI_SPAWNER)
        dropSelf(ModBlocks.TIN_ORE)
        dropSelf(ModBlocks.TIN_BLOCK)
        dropSelf(ModBlocks.BRONZE_BLOCK)
        dropSelf(ModBlocks.STEEL_BLOCK)
        dropSelf(ModBlocks.FIFI_LOG)
        dropSelf(ModBlocks.FIFI_WOOD)
        dropSelf(ModBlocks.STRIPPED_FIFI_LOG)
        dropSelf(ModBlocks.STRIPPED_FIFI_WOOD)
        dropSelf(ModBlocks.FIFI_PLANKS)
        dropSelf(ModBlocks.FIFI_STAIRS)
        dropSelf(ModBlocks.FIFI_SLAB)
        dropSelf(ModBlocks.FIFI_FENCE)
        dropSelf(ModBlocks.FIFI_FENCE_GATE)

        add(ModBlocks.BISMUTH_ORE) {
            createOreDrop(ModBlocks.BISMUTH_ORE, ModItems.BISMUTH)
        }
        add(ModBlocks.DEEPSLATE_BISMUTH_ORE) {
            createOreDrop(ModBlocks.DEEPSLATE_BISMUTH_ORE, ModItems.BISMUTH)
        }

        add(ModBlocks.POTATO_BLOCK) {
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                        .add(
                            LootItem.lootTableItem(Items.BAKED_POTATO)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3f, 9f)))
                                .`when`(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.PETI_ITEM)))
                                .otherwise(LootItem.lootTableItem(ModBlocks.POTATO_BLOCK))
                        )
                )
        }

        add(ModBlocks.FIFI_LEAVES) {
            createLeavesDrops(it, ModBlocks.FIFI_PLANKS, 0.025f, 0.027777778f, 0.03125f, 0.041666668f, 0.1f)
        }
    }

    override fun getKnownBlocks(): Iterable<Block> =
        ModBlocks.REGISTRY.entries.map(RegistryObject<Block>::get).asIterable()
}