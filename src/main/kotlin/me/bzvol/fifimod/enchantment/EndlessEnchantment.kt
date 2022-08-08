package me.bzvol.fifimod.enchantment

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class EndlessEnchantment(pRarity: Rarity, vararg pApplicableSlots: EquipmentSlot) :
    Enchantment(pRarity, EnchantmentCategory.BOW, pApplicableSlots) {
    override fun getMinCost(pLevel: Int): Int = 20
    override fun getMaxCost(pLevel: Int): Int = 50
    override fun getMaxLevel(): Int = 1
}