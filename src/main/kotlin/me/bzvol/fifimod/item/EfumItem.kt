package me.bzvol.fifimod.item

import me.bzvol.fifimod.entity.projectile.EfumEntity
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class EfumItem(properties: Properties) : Item(properties) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemstack = pPlayer.getItemInHand(pUsedHand)
        pLevel.playSound(null, pPlayer.x, pPlayer.y, pPlayer.z, SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (pLevel.random.nextFloat() * 0.4f + 0.8f))

        if (!pLevel.isClientSide) {
            val efum = EfumEntity(pLevel, pPlayer)
            efum.item = itemstack
            efum.shootFromRotation(pPlayer, pPlayer.xRot, pPlayer.yRot, 0f, 1.5f, 1f)
            pLevel.addFreshEntity(efum)
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this))
        if (!pPlayer.abilities.instabuild)
            itemstack.shrink(1)

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide)
    }
}