package me.bzvol.fifimod.data.loot

import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.item.ModItems
import net.minecraft.data.loot.EntityLoot
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraftforge.registries.RegistryObject

class ModEntityLoot : EntityLoot() {
    override fun addTables() {
        add(
            ModEntityTypes.FIFI,
            LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(
                    LootItem.lootTableItem(
                        ModItems.FIFHRANY_SEEDS
                    ).apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 3f)))
                )
            )
        )

        add(
            ModEntityTypes.LITULY,
            LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(
                    LootItem.lootTableItem(
                        Items.MELON_SEEDS
                    ).apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, 2f)))
                )
            )
        )
    }

    override fun getKnownEntities(): Iterable<EntityType<*>> =
        ModEntityTypes.REGISTRY.entries.map(RegistryObject<EntityType<*>>::get).asIterable()
}