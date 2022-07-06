package me.bzvol.fifimod.item

import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
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

        /*if (!pLevel.isClientSide) {
             Snowball snowball = new Snowball(pLevel, pPlayer);
             snowball.setItem(itemstack);
             snowball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
             pLevel.addFreshEntity(snowball);
          }

          pPlayer.awardStat(Stats.ITEM_USED.get(this));
          if (!pPlayer.getAbilities().instabuild) {
             itemstack.shrink(1);
          }*/

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide)
    }
}