package me.bzvol.fifimod.block

import me.bzvol.fifimod.item.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.Mth
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

class FifhranyBlock(pProperties: Properties) : CropBlock(pProperties) {
    companion object {
        const val MAX_AGE = 8
        val AGE: IntegerProperty = IntegerProperty.create("age", 0, 8)
        val HEIGHT_BY_AGE = listOf(3.0, 6.0, 8.0, 10.0, 11.0, 13.0, 14.0, 16.0, 16.0)
    }

    override fun getAgeProperty(): IntegerProperty = AGE
    override fun getMaxAge(): Int = MAX_AGE

    override fun getBaseSeedId(): ItemLike = ModItems.FIFHRANY_SEEDS

    @Deprecated("Deprecated in Java")
    override fun randomTick(pState: BlockState, pLevel: ServerLevel, pPos: BlockPos, pRandom: Random) {
        super.randomTick(pState, pLevel, pPos, pRandom)
    }

    override fun getBonemealAgeIncrease(pLevel: Level): Int = Mth.nextInt(pLevel.random, 0, 1)

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(AGE)
    }

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape = Block.box(
        .0, .0, .0, 16.0,
        HEIGHT_BY_AGE[pState.getValue(this.ageProperty)], 16.0
    )
}