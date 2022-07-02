package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class FifiSpawnerBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {
    companion object {
        private val SHAPE: VoxelShape = Block.box(.0, .0, .0, 16.0, 9.0, 16.0)
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape = SHAPE

    // TODO: light and start spawning
    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        pLevel.explode(
            null,
            pPos.x + .0, pPos.y + .0, pPos.z + .0, 3f,
            true, Explosion.BlockInteraction.DESTROY,
        )
        return InteractionResult.CONSUME
    }
}