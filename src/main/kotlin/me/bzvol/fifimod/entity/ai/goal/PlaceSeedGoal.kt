package me.bzvol.fifimod.entity.ai.goal

import me.bzvol.fifimod.block.FifhranyBlock
import me.bzvol.fifimod.block.ModBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState

class PlaceSeedGoal(pMob: PathfinderMob, pSeedBlock: BlockState, pSpeedModifier: Double) :
    MoveToBlockGoal(pMob, pSpeedModifier, 12) {
    private val seedBlock: BlockState = pSeedBlock

    override fun canUse(): Boolean = this.mob.health == this.mob.maxHealth && super.canUse()
    override fun canContinueToUse(): Boolean = this.mob.health == this.mob.maxHealth && super.canContinueToUse()

    override fun tick() {
        super.tick()
        if (this.isReachedTarget) {
            this.mob.level.setBlock(this.blockPos.above(), this.seedBlock, 3)
        }
    }

    override fun isValidTarget(pLevel: LevelReader, pPos: BlockPos): Boolean =
        pLevel.isEmptyBlock(pPos.above()) && pLevel.getBlockState(pPos).`is`(Blocks.FARMLAND)
}