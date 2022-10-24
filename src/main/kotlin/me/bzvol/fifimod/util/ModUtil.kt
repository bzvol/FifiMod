package me.bzvol.fifimod.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries
import java.util.Random

object ModUtil {
    fun <T> weightedRandom(random: Random, iterable: Iterable<T>, weight: (T) -> Int): T {
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

    fun <T> weightedRandom(random: Random, iterable: Iterable<T>, weights: Iterable<Int>): T {
        val iterator = weights.iterator()
        return weightedRandom(random, iterable) { iterator.next() }
    }

    val Item.registryName: ResourceLocation?
        get() = ForgeRegistries.ITEMS.getKey(this)

    val Block.registryName: ResourceLocation?
        get() = ForgeRegistries.BLOCKS.getKey(this)
}