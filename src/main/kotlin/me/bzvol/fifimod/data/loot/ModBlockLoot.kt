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
    }

    override fun getKnownBlocks(): Iterable<Block> =
        ModBlocks.REGISTRY.entries.map(RegistryObject<Block>::get).asIterable()
}