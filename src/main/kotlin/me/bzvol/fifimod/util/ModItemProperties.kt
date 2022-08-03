package me.bzvol.fifimod.util

import me.bzvol.fifimod.item.ModItems
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item

object ModItemProperties {
    fun addItemProperties() {
        makeBow(ModItems.MODERN_BOW)
    }

    private fun makeBow(pItem: Item) {
        ItemProperties.register(pItem, ResourceLocation("pull")) { pStack, _, pEntity, _ ->
            if (pEntity == null) 0f
            else if (pEntity.useItem != pStack) 0f
            else (pStack.useDuration - pEntity.useItemRemainingTicks).toFloat() / 20f
        }

        ItemProperties.register(pItem, ResourceLocation("pulling")) { pStack, _, pEntity, _ ->
            if (pEntity != null && pEntity.isUsingItem && pEntity.useItem == pStack) 1f else 0f
        }
    }
}