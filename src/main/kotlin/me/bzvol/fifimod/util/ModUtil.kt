package me.bzvol.fifimod.util

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.Dynamic
import com.mojang.serialization.JsonOps
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.common.loot.IGlobalLootModifier.getJson
import net.minecraftforge.common.loot.LootModifierManager
import net.minecraftforge.registries.ForgeRegistries

object ModUtil {
    fun <T> weightedRandom(random: RandomSource, iterable: Iterable<T>, weight: (T) -> Int): T {
        val totalWeight = iterable.sumOf(weight)
        val randomWeight = random.nextInt(totalWeight)
        var currentWeight = 0
        for (element in iterable) {
            currentWeight += weight(element)
            if (currentWeight >= randomWeight) {
                return element
            }
        }
        throw IllegalStateException("No element found")
    }

    fun <T> weightedRandom(random: RandomSource, iterable: Iterable<T>, weights: Iterable<Int>): T {
        val iterator = weights.iterator()
        return weightedRandom(random, iterable) { iterator.next() }
    }

    val Item.registryName: ResourceLocation?
        get() = ForgeRegistries.ITEMS.getKey(this)

    val Block.registryName: ResourceLocation?
        get() = ForgeRegistries.BLOCKS.getKey(this)

    // Source: https://forums.minecraftforge.net/topic/74979-1144-rotate-voxel-shapes/
    // Coded by @wyn_price
    fun VoxelShape.rotate(from: Direction, to: Direction): VoxelShape {
        val buffer = arrayOf<VoxelShape>(this, Shapes.empty())

        val times: Int = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4
        for (i in 0 until times) {
            buffer[0].forAllBoxes { pMinX, pMinY, pMinZ, pMaxX, pMaxY, pMaxZ ->
                buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - pMaxZ, pMinY, pMinX, 1 - pMinZ, pMaxY, pMaxX))
            }
            buffer[0] = buffer[1]
            buffer[1] = Shapes.empty()
        }

        return buffer[0]
    }

    val LOOT_FUNCTIONS_CODEC: Codec<Array<LootItemFunction>>
        get() = Codec.PASSTHROUGH.flatXmap(
            { d ->
                try {
                    val functions = LootModifierManager.GSON_INSTANCE.fromJson(
                        getJson<Array<LootItemFunction>>(d),
                        Array<LootItemFunction>::class.java
                    )
                    DataResult.success(functions)
                } catch (e: Exception) {
                    LootModifierManager.LOGGER.warn("Unable to decode loot functions", e)
                    DataResult.error(e.message)
                }
            },
            { functions ->
                try {
                    val element = LootModifierManager.GSON_INSTANCE.toJsonTree(functions)
                    DataResult.success(Dynamic(JsonOps.INSTANCE, element))
                } catch (e: Exception) {
                    LootModifierManager.LOGGER.warn("Unable to encode loot functions", e)
                    DataResult.error(e.message)
                }
            },
        )
}