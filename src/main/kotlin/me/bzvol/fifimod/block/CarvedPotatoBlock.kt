package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Wearable
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.pattern.BlockInWorld
import net.minecraft.world.level.block.state.pattern.BlockPattern
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate
import net.minecraft.world.level.block.state.predicate.BlockPredicate
import net.minecraft.world.level.material.Material
import java.util.*

class CarvedPotatoBlock(pProperties: Properties) : HorizontalDirectionalBlock(pProperties), Wearable {
    private var potatoMonsterPattern: BlockPattern? = null

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)

    @Deprecated("Deprecated in Java")
    override fun onPlace(pState: BlockState, pLevel: Level, pPos: BlockPos, pOldState: BlockState, pIsMoving: Boolean) {
        if (!pOldState.`is`(pState.block))
            this.trySpawnMonster(pLevel, pPos)
    }

    private fun trySpawnMonster(pLevel: Level, pPos: BlockPos) {
        val blockpatternmatch = getOrCreatePattern()?.find(pLevel, pPos)
        if (this.potatoMonsterPattern != null && blockpatternmatch != null) {
            pLevel.players().forEach {
                it.sendSystemMessage(Component.literal("Ja. Itt majd kéne így spawnolnia egy krumpliszörnynek. Jeeee"))
            }
            /*for (i in 0 until this.potatoMonsterPattern!!.width) {
                for (j in 0 until this.potatoMonsterPattern!!.height) {
                    val blockinworld = blockpatternmatch.getBlock(i, j, 0)
                    pLevel.setBlock(blockinworld.pos, Blocks.AIR.defaultBlockState(), 2)
                    pLevel.levelEvent(2001, blockinworld.pos, Block.getId(blockinworld.state))
                }
            }*/
        }
    }

    private fun getOrCreatePattern(): BlockPattern? {
        if (this.potatoMonsterPattern == null) {
            this.potatoMonsterPattern = BlockPatternBuilder.start()
                .aisle("~~~^~~~", "~~%#%~~", "#######", "%~~#~~%", "~~###~~", "~~#~#~~", "~~%~%~~")
                .where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR)))
                .where('#', BlockInWorld.hasState(BlockPredicate.forBlock(ModBlocks.POTATO_BLOCK)))
                .where('^', BlockInWorld.hasState(BlockPredicate.forBlock(ModBlocks.CARVED_POTATO_BLOCK)))
                .where('%', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.DIRT)))
                .build()
        }

        return this.potatoMonsterPattern
    }
}