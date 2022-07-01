package me.bzvol.fifimod.block

import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition

class BismuthBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {
    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)
}