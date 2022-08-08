package me.bzvol.fifimod.enchantment

import me.bzvol.fifimod.FifiMod
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModEnchantments {
    private val REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, FifiMod.MOD_ID)
    private val OVERRIDE_REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft")

    private val ENDLESS by OVERRIDE_REGISTRY.registerObject("infinity") {
        EndlessEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND)
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
        OVERRIDE_REGISTRY.register(eventBus)
    }
}