package me.bzvol.fifimod.block

import me.bzvol.fifimod.sound.ModSounds
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.BlockHitResult

class BurpboxBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {
    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)

    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        if (pLevel.isClientSide) return InteractionResult.SUCCESS

        pLevel.playSound(null, pPos, ModSounds.BURP, SoundSource.BLOCKS, 3f, 1f)

        // X+: x+1.0 y+0.5 z+0.5
        // X-: x+0.0 y+0.5 z+0.5
        // Z+: x+0.5 y+0.5 z+1.0
        // Z-: x+0.5 y+0.5 z+0.0
        val axis = pState.getValue(FACING).axis
        val axisDir = pState.getValue(FACING).axisDirection.step
        val axisDir2 = if (axisDir == 1) 1.0 else 0.0
        val x = if (axis == Direction.Axis.X) axisDir2 else 0.5
        val z = if (axis == Direction.Axis.Z) axisDir2 else 0.5
        val xSpeed = if (axis == Direction.Axis.X) 0.2 * axisDir else 0.0
        val zSpeed = if (axis == Direction.Axis.Z) 0.2 * axisDir else 0.0
        pLevel.addParticle(
            ParticleTypes.BUBBLE,
            pPos.x + x, pPos.y + 0.5, pPos.z + z, // Spawn particle in direction of the block
            xSpeed, 0.0, zSpeed
        )

        return InteractionResult.CONSUME
    }


}