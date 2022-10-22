package me.bzvol.fifimod.item

import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ArrowItem
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Level
import net.minecraftforge.event.ForgeEventFactory

class ModernBowItem(pProperties: Properties) : BowItem(pProperties) {
    override fun releaseUsing(pStack: ItemStack, pLevel: Level, pEntityLiving: LivingEntity, pTimeLeft: Int) {
        if (pEntityLiving is Player) {
            val flag = pEntityLiving.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(
                Enchantments.INFINITY_ARROWS,
                pStack
            ) > 0
            var itemstack = pEntityLiving.getProjectile(pStack)
            var i = getUseDuration(pStack) - pTimeLeft
            i = ForgeEventFactory.onArrowLoose(pStack, pLevel, pEntityLiving, i, !itemstack.isEmpty || flag)
            if (i < 0) return
            if (!itemstack.isEmpty || flag) {
                if (itemstack.isEmpty) itemstack = ItemStack(Items.ARROW)
                val f = getPowerForTime(i)
                if (f.toDouble() >= 0.1) {
                    val flag1 =
                        pEntityLiving.abilities.instabuild || itemstack.item is ArrowItem && (itemstack.item as ArrowItem).isInfinite(
                            itemstack,
                            pStack,
                            pEntityLiving
                        )
                    if (!pLevel.isClientSide) {
                        val arrowitem = (if (itemstack.item is ArrowItem) itemstack.item else Items.ARROW) as ArrowItem
                        var abstractarrow = arrowitem.createArrow(pLevel, itemstack, pEntityLiving)
                        abstractarrow = customArrow(abstractarrow)
                        abstractarrow.shootFromRotation(
                            pEntityLiving,
                            pEntityLiving.xRot,
                            pEntityLiving.yRot,
                            0.0f,
                            f * 3.0f,
                            1.0f
                        )

                        // CUSTOM DAMAGE
                        abstractarrow.baseDamage = 4.0

                        if (f == 1.0f) abstractarrow.isCritArrow = true
                        val j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack)
                        if (j > 0) abstractarrow.baseDamage = abstractarrow.baseDamage + j.toDouble() * 0.5 + 0.5
                        val k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack)
                        if (k > 0) abstractarrow.knockback = k
                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0)
                            abstractarrow.setSecondsOnFire(100)
                        pStack.hurtAndBreak(1, pEntityLiving) { player: Player ->
                            player.broadcastBreakEvent(
                                pEntityLiving.usedItemHand
                            )
                        }
                        if (flag1 || pEntityLiving.abilities.instabuild && (itemstack.`is`(Items.SPECTRAL_ARROW) || itemstack.`is`(
                                Items.TIPPED_ARROW
                            ))
                        ) abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY
                        pLevel.addFreshEntity(abstractarrow)
                    }
                    pLevel.playSound(
                        null,
                        pEntityLiving.x,
                        pEntityLiving.y,
                        pEntityLiving.z,
                        SoundEvents.ARROW_SHOOT,
                        SoundSource.PLAYERS,
                        1.0f,
                        1.0f / (pLevel.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f
                    )
                    if (!flag1 && !pEntityLiving.abilities.instabuild) {
                        itemstack.shrink(1)
                        if (itemstack.isEmpty) pEntityLiving.inventory.removeItem(itemstack)
                    }
                    pEntityLiving.awardStat(Stats.ITEM_USED[this])
                }
            }
        }
    }
}