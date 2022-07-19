package me.bzvol.fifimod.block.entity

/*
import me.bzvol.fifimod.entity.FifiEntity
import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class FifiSpawnerBlockEntity(
    pWorldPosition: BlockPos,
    pBlockState: BlockState
) : BlockEntity(ModBlockEntities.FIFI_SPAWNER_ENTITY, pWorldPosition, pBlockState) {

    companion object {
        var isSpawningEntity: Boolean = false
        private var entity: FifiEntity? = null
        private var entityRotate: Int = 0
        fun tick(
            pLevel: Level,
            pPos: BlockPos,
            pState: BlockState,
            pBlockEntity: FifiSpawnerBlockEntity
        ) {
            if (isSpawningEntity && !pLevel.isClientSide) {
                if (entity == null) {
                    entity = ModEntityTypes.FIFI.spawn(
                        pLevel as ServerLevel,
                        null, null, null,
                        pPos.above(2),
                        MobSpawnType.SPAWNER, false, false
                    )
                    entity?.yRot = pState.getValue(HorizontalDirectionalBlock.FACING).toYRot()
                    entity?.isInvulnerable = true // Make the entity invulnerable to avoid attacks,
                    entity?.isNoAi = true // Freeze entity, while playing spawn animation on it.
                } else {
                    if (entityRotate < 360) { // Rotate the entity
                        entity!!.yRot = (entity!!.yRot + 1) % 360
                        ++entityRotate
                    } else {
                        entity!!.yRot = pState.getValue(HorizontalDirectionalBlock.FACING).toYRot()
                        pLevel.explode(
                            null,
                            pPos.x + .0, pPos.y + .0, pPos.z + .0,
                            1f, false,
                            Explosion.BlockInteraction.NONE
                        )
                        entity!!.isNoAi = false
                        entity!!.isInvulnerable = false
                        entity = null
                        entityRotate = 0
                        isSpawningEntity = false
                        pLevel.destroyBlock(pPos, false)
                        pBlockEntity.setRemoved()
                    }
                }
            }
        }
    }
}*/
