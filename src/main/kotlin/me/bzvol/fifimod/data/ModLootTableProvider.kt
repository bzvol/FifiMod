package me.bzvol.fifimod.data

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import me.bzvol.fifimod.data.loot.ModBlockLoot
import me.bzvol.fifimod.data.loot.ModEntityLoot
import net.minecraft.data.DataGenerator
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.LootTables
import net.minecraft.world.level.storage.loot.ValidationContext
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ModLootTableProvider(generator: DataGenerator) : LootTableProvider(generator) {
    private val lootTables: List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> =
        ImmutableList.of(
            Pair.of(Supplier(::ModBlockLoot), LootContextParamSets.BLOCK),
            Pair.of(Supplier(::ModEntityLoot), LootContextParamSets.ENTITY)
        )

    override fun getTables(): List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> =
        lootTables

    override fun validate(map: MutableMap<ResourceLocation, LootTable>, validationtracker: ValidationContext) {
        map.forEach { (id, table) -> LootTables.validate(validationtracker, id, table) }
    }
}