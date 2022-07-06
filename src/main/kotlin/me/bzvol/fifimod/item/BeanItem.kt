package me.bzvol.fifimod.item

import me.bzvol.fifimod.sound.ModSounds
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class BeanItem(properties: Properties) : Item(properties) {
    override fun finishUsingItem(pStack: ItemStack, pLevel: Level, pLivingEntity: LivingEntity): ItemStack {
        val itemstack = super.finishUsingItem(pStack, pLevel, pLivingEntity)

        pLevel.playSound(null, pLivingEntity.x, pLivingEntity.y, pLivingEntity.z, ModSounds.BURP, SoundSource.PLAYERS, 1f, pLevel.random.nextFloat() * 0.4F + 0.8F)

        return itemstack
    }
}