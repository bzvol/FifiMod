package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult
import net.minecraftforge.common.ToolAction
import net.minecraftforge.common.ToolActions

class PotatoBlock(pProperties: Properties) : Block(pProperties) {
    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        val itemstack = pPlayer.getItemInHand(pHand)
        return if (itemstack.canPerformAction(ToolActions.SHEARS_CARVE)) {
            var dir = pHit.direction
            dir = if (dir.axis == Direction.Axis.Y) pPlayer.direction.opposite else dir
            pLevel.playSound(null, pPos, SoundEvents.MOOSHROOM_SHEAR, SoundSource.BLOCKS, 1f, 1f)
            pLevel.setBlock(
                pPos,
                ModBlocks.CARVED_POTATO_BLOCK.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, dir),
                11
            )
            val itementity = ItemEntity(
                pLevel,
                pPos.x + .5 + dir.stepX * .65,
                pPos.y + .1,
                pPos.z + .5 + dir.stepZ * .65,
                ItemStack(Items.POTATO, 2)
            )
            itementity.setDeltaMovement(.05 * dir.stepX + pLevel.random.nextDouble() * .02, .05, .05 * dir.stepZ + pLevel.random.nextDouble() * .02)
            pLevel.addFreshEntity(itementity)
            itemstack.hurtAndBreak(1, pPlayer) {
                it.broadcastBreakEvent(pHand)
            }
            pLevel.gameEvent(pPlayer, GameEvent.SHEAR, pPos)
            pPlayer.awardStat(Stats.ITEM_USED.get(Items.SHEARS))

            InteractionResult.sidedSuccess(pLevel.isClientSide)
        } else super.use(pState, pLevel, pPos, pPlayer, pHand, pHit)
    }
}