package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction

class ModFlammableRotatedPillarBlock(properties: Properties) : RotatedPillarBlock(properties) {
    override fun isFlammable(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        direction: Direction
    ): Boolean = true

    override fun getFlammability(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        direction: Direction
    ): Int = 5

    override fun getFireSpreadSpeed(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        direction: Direction
    ): Int = 5

    override fun getToolModifiedState(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        stack: ItemStack,
        toolAction: ToolAction
    ): BlockState? {
        if (stack.item is AxeItem) {
            if (state.`is`(ModBlocks.FIFI_LOG))
                return ModBlocks.STRIPPED_FIFI_LOG.defaultBlockState().setValue(AXIS, state.getValue(AXIS))
            if (state.`is`(ModBlocks.FIFI_WOOD))
                return ModBlocks.STRIPPED_FIFI_WOOD.defaultBlockState().setValue(AXIS, state.getValue(AXIS))
        }

        return super.getToolModifiedState(state, level, pos, player, stack, toolAction)
    }
}