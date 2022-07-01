package me.bzvol.fifimod.block

import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.stream.Stream

class FifiSpawnerBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {
    val SHAPE: VoxelShape = Stream.of(
        Block.box(.0, .0, .0, 16.0, 16.0, 16.0)
    ).reduce { v1, v2 -> Shapes.join(v1, v2, BooleanOp.OR) }.get()

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)
}
