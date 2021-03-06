package me.bzvol.fifimod.util

import me.bzvol.fifimod.FifiMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object ModTags {
    object Blocks {
        val FIFI_LOGS: TagKey<Block> = tag("fifi_logs")

        private fun tag(name: String): TagKey<Block> {
            return BlockTags.create(ResourceLocation(FifiMod.MOD_ID, name))
        }

        private fun forgeTag(name: String): TagKey<Block> {
            return BlockTags.create(ResourceLocation("forge", name))
        }
    }

    object Items {
        val FIFI_LOGS: TagKey<Item> = tag("fifi_logs")

        private fun tag(name: String): TagKey<Item> {
            return ItemTags.create(ResourceLocation(FifiMod.MOD_ID, name))
        }

        private fun forgeTag(name: String): TagKey<Item> {
            return ItemTags.create(ResourceLocation("forge", name))
        }
    }
}