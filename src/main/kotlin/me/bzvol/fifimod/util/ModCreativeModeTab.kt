package me.bzvol.fifimod.util

import me.bzvol.fifimod.item.ModItems
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object ModCreativeModeTab {
    val FIFI_TAB: CreativeModeTab = object : CreativeModeTab("fifi_tab") {
        override fun makeIcon(): ItemStack = ItemStack(ModItems.THE_FIFHER)
    }

    val PETHINGS_TAB: CreativeModeTab = object : CreativeModeTab("pethings_tab") {
        override fun makeIcon(): ItemStack = ItemStack(ModItems.PETI_ITEM)
    }
}