@file:Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")

package me.bzvol.fifimod.block

import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

class FifiSpawnerBlock(properties: Properties) : Block(properties) {
    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val LIT: BooleanProperty = BlockStateProperties.LIT

        private val SHAPE: VoxelShape = box(.0, .0, .0, 16.0, 9.0, 16.0)

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

    @Deprecated("Deprecated in Java")
    override fun rotate(pState: BlockState, pRotation: Rotation): BlockState =
        pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)))

    @Deprecated("Deprecated in Java")
    override fun mirror(pState: BlockState, pMirror: Mirror): BlockState =
        pState.rotate(pMirror.getRotation(pState.getValue(FACING)))

    @Deprecated("Deprecated in Java")
    override fun neighborChanged(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pBlock: Block,
        pFromPos: BlockPos,
        pIsMoving: Boolean
    ) {
        if (!pLevel.isClientSide) {
            val flag = pState.getValue(LIT)
            if (flag != pLevel.hasNeighborSignal(pPos))
                if (flag) pLevel.scheduleTick(pPos, this, 4)
                else pLevel.setBlock(pPos, pState.cycle(LIT), 2)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun tick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: Random) {
        if (pState.getValue(LIT) && !pLevel.hasNeighborSignal(pPos))
            pLevel.setBlock(pPos, pState.cycle(LIT), 2)
    }

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape = SHAPE

    @Deprecated("Deprecated in Java")
    override fun getRenderShape(pState: BlockState): RenderShape = RenderShape.MODEL

    @Suppress("ControlFlowWithEmptyBody")
    fun light(pLevel: Level, pState: BlockState, pPos: BlockPos) {
        if (pState.getValue(LIT)) {
            val mob = ModEntityTypes.FIFI.spawn(
                pLevel as ServerLevel,
                null, null, null,
                pPos.above(2),
                MobSpawnType.SPAWNER, false, false
            )
            mob?.startSpawning(pState, pPos)
        }
    }

}
