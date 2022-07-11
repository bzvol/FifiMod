@file:Suppress("DeprecatedCallableAddReplaceWith")

package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

class FifiSpawnerBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {
    companion object {
        val LIT: BooleanProperty = BlockStateProperties.LIT

        private val SHAPE: VoxelShape = Block.box(.0, .0, .0, 16.0, 9.0, 16.0)

        fun canLight(pState: BlockState): Boolean =
            pState.`is`(ModBlocks.FIFI_SPAWNER) && pState.hasProperty(LIT) && pState.getValue(LIT)
    }

    init {
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false))
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState()
            .setValue(FACING, pContext.horizontalDirection.opposite)
            .setValue(LIT, pContext.level.hasNeighborSignal(pContext.clickedPos))

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
        pBuilder.add(LIT)
    }

    override fun onNeighborChange(state: BlockState, level: LevelReader, pos: BlockPos, neighbor: BlockPos) {
        super.onNeighborChange(state, level, pos, neighbor)
    }

    @Deprecated("Deprecated in Java")
    override fun tick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: Random) {
        super.tick(pState, pLevel, pPos, pRandom)
    }

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape = SHAPE

    /*pLevel.explode(
        null,
        pPos.x + .0, pPos.y + .0, pPos.z + .0, 3f,
        true, Explosion.BlockInteraction.DESTROY,
    )*/
    fun light() {

    }

}
