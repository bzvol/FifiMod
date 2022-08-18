package me.bzvol.fifimod.block

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.Wearable
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class AquariumBlock(properties: Properties) : Block(properties), Wearable {
    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val FILL: EnumProperty<Fill> = EnumProperty.create("fill", Fill::class.java)

        private val SHAPE = box(.0, .0, .0, 16.0, 14.0, 16.0)
    }

    init {
        this.registerDefaultState(this.defaultBlockState().setValue(FILL, Fill.EMPTY))
    }

    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? =
        this.defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
        pBuilder.add(FILL)
    }

    @Deprecated("Deprecated in Java")
    override fun rotate(pState: BlockState, pRotation: Rotation): BlockState =
        pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)))

    @Deprecated("Deprecated in Java")
    override fun mirror(pState: BlockState, pMirror: Mirror): BlockState =
        pState.rotate(pMirror.getRotation(pState.getValue(FACING)))

    @Deprecated("Deprecated in Java")
    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape =
        when (pState.getValue(FILL)) {
            Fill.EMPTY -> Shapes.join(
                box(.0, .0, .0, 16.0, 14.0, 16.0),
                box(1.0, 1.0, 1.0, 15.0, 14.0, 15.0),
                BooleanOp.ONLY_FIRST
            )
            else -> Shapes.join(
                box(.0, .0, .0, 16.0, 14.0, 16.0),
                box(1.0, 6.0, 1.0, 15.0, 14.0, 15.0),
                BooleanOp.ONLY_FIRST
            )
        }

    @Deprecated("Deprecated in Java")
    override fun getRenderShape(pState: BlockState): RenderShape = RenderShape.MODEL

    @Deprecated("Deprecated in Java")
    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        val itemstack = pPlayer.getItemInHand(pHand)
        return if (Fill.isValidFill(itemstack.item)) {
            val fill = Fill.fromItem(itemstack.item)
            pLevel.setBlock(pPos, pState.setValue(FILL, fill), 2)
            pLevel.playSound(
                null, pPos.x + .0, pPos.y + .0, pPos.z + .0,
                fill.sound, SoundSource.BLOCKS, 1f, 1f
            )
            if (!pPlayer.abilities.instabuild)
                itemstack.shrink(1)

            InteractionResult.sidedSuccess(pLevel.isClientSide)
        } else InteractionResult.PASS
    }

    enum class Fill(val block: Block, val sound: SoundEvent) : StringRepresentable {
        EMPTY(Blocks.AIR, SoundEvents.GLASS_BREAK),
        SAND(Blocks.SAND, SoundEvents.SAND_PLACE),
        RED_SAND(Blocks.RED_SAND, SoundEvents.SAND_PLACE),
        DIRT(Blocks.DIRT, SoundEvents.ROOTED_DIRT_PLACE);

        val modelName = this.name.lowercase()

        override fun toString(): String = block.registryName!!.toString()
        override fun getSerializedName(): String = this.name.lowercase()

        companion object {
            fun isValidFill(pItem: Item): Boolean =
                values().any { it.block.registryName!!.toString() == pItem.registryName!!.toString() }

            fun fromItem(pItem: Item): Fill =
                values().first { it.block.registryName!!.toString() == pItem.registryName!!.toString() }
        }
    }
}