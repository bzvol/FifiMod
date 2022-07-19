package me.bzvol.fifimod.item

import me.bzvol.fifimod.block.FifiSpawnerBlock
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.FlintAndSteelItem
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.BaseFireBlock
import net.minecraft.world.level.block.CampfireBlock
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.CandleCakeBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.gameevent.GameEvent

class AmethystLighterItem(properties: Properties) : FlintAndSteelItem(properties) {
    override fun useOn(pContext: UseOnContext): InteractionResult {
        val player = pContext.player
        val level = pContext.level
        val blockpos = pContext.clickedPos
        val blockstate = level.getBlockState(blockpos)
        return if (
            !CampfireBlock.canLight(blockstate) &&
            !CandleBlock.canLight(blockstate) &&
            !CandleCakeBlock.canLight(blockstate) &&
            !FifiSpawnerBlock.canLight(blockstate)
        ) {
            val blockpos1 = blockpos.relative(pContext.clickedFace)
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, pContext.horizontalDirection)) {
                level.playSound(
                    player,
                    blockpos1,
                    SoundEvents.FLINTANDSTEEL_USE,
                    SoundSource.BLOCKS,
                    1.0f,
                    level.random.nextFloat() * 0.4f + 0.8f
                )
                val blockstate1 = BaseFireBlock.getState(level, blockpos1)
                level.setBlock(blockpos1, blockstate1, 11)
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos)
                val itemstack = pContext.itemInHand
                if (player is ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger(player, blockpos1, itemstack)
                    itemstack.hurtAndBreak<Player>(1, player) { pPlayer: Player ->
                        pPlayer.broadcastBreakEvent(
                            pContext.hand
                        )
                    }
                }
                InteractionResult.sidedSuccess(level.isClientSide)
            } else {
                InteractionResult.FAIL
            }
        } else {
            level.playSound(
                player,
                blockpos,
                SoundEvents.FLINTANDSTEEL_USE,
                SoundSource.BLOCKS,
                1.0f,
                level.random.nextFloat() * 0.4f + 0.8f
            )

            if (!FifiSpawnerBlock.canLight(blockstate))
                level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, true), 11)
            else if (!level.isClientSide)
                (blockstate.block as FifiSpawnerBlock).light(level, blockstate, blockpos)

            level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos)
            if (player != null) {
                pContext.itemInHand.hurtAndBreak(1, player) { pPlayer: Player ->
                    pPlayer.broadcastBreakEvent(pContext.hand)
                }
            }
            InteractionResult.sidedSuccess(level.isClientSide)
        }
    }
}