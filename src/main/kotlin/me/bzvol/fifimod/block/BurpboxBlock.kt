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
import net.minecraft.world.phys.Vec3

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
        if (!pLevel.isClientSide)
            pLevel.playSound(null, pPos, ModSounds.BURP, SoundSource.BLOCKS, 1f, 1f)

        else {
            val dir = pState.getValue(FACING)

            val pos = pPos.getCenterOfDirection(dir, 0.3)

            val vX = if (dir.axis == Direction.Axis.X) 0.2 * dir.axisDirection.step else 0.0
            val vY = 0.0
            val vZ = if (dir.axis == Direction.Axis.Z) 0.2 * dir.axisDirection.step else 0.0

            pLevel.addParticle(
                ParticleTypes.EXPLOSION,
                pos.x, pos.y, pos.z,
                vX, vY, vZ
            )
        }

        return InteractionResult.CONSUME
    }

    private fun BlockPos.getCenterOfDirection(dir: Direction, additional: Double = 0.0): Vec3 = when (dir) {
        Direction.DOWN ->   Vec3(this.x + 0.5, this.y + 0.0 - additional, this.z + 0.5)
        Direction.UP ->     Vec3(this.x + 0.5, this.y + 1.0 + additional, this.z + 0.5)
        Direction.NORTH ->  Vec3(this.x + 0.5, this.y + 0.5, this.z + 0.0 - additional)
        Direction.SOUTH ->  Vec3(this.x + 0.5, this.y + 0.5, this.z + 1.0 + additional)
        Direction.WEST ->   Vec3(this.x + 0.0 - additional, this.y + 0.5, this.z + 0.5)
        Direction.EAST ->   Vec3(this.x + 1.0 + additional, this.y + 0.5, this.z + 0.5)
    }

}