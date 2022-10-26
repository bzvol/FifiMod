package me.bzvol.fifimod.entity

import me.bzvol.fifimod.entity.variant.LitulyVariant
import me.bzvol.fifimod.util.ModUtil
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class LitulyEntity(entityType: EntityType<out PathfinderMob>, level: Level) : PathfinderMob(entityType, level),
    IAnimatable {
    private val factory: AnimationFactory = AnimationFactory(this)
    private val controller =
        AnimationController(this, "lituly_controller", 0f, this::predicate)

    var variant: LitulyVariant
        get() = LitulyVariant.byId(this.entityData.get(DATA_ID_TYPE_VARIANT) and 255)
        set(value) = this.entityData.set(DATA_ID_TYPE_VARIANT, value.id and 255)
    private val typeVariant: Int
        get() = this.entityData.get(DATA_ID_TYPE_VARIANT)

    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation("animation.lituly.move", ILoopType.EDefaultLoopTypes.LOOP)
            )
            return PlayState.CONTINUE
        }

        return PlayState.STOP
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(this.controller)
    }

    override fun getFactory(): AnimationFactory = this.factory

    override fun registerGoals() {
        this.goalSelector.addGoal(1, FloatGoal(this))
        this.goalSelector.addGoal(2, PanicGoal(this, 1.2))
        this.goalSelector.addGoal(3, TemptGoal(this, 1.1, Ingredient.of(Items.MELON_SEEDS), false))
        this.goalSelector.addGoal(4, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(5, RandomLookAroundGoal(this))
        this.goalSelector.addGoal(6, HurtByTargetGoal(this).setAlertOthers())
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0)
    }

    override fun readAdditionalSaveData(pCompound: CompoundTag) {
        super.readAdditionalSaveData(pCompound)
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"))
    }

    override fun addAdditionalSaveData(pCompound: CompoundTag) {
        super.addAdditionalSaveData(pCompound)
        pCompound.putInt("Variant", this.typeVariant)
    }

    override fun finalizeSpawn(
        pLevel: ServerLevelAccessor,
        pDifficulty: DifficultyInstance,
        pReason: MobSpawnType,
        pSpawnData: SpawnGroupData?,
        pDataTag: CompoundTag?
    ): SpawnGroupData? {
        this.variant = ModUtil.weightedRandom(this.random, LitulyVariant.values().asIterable(), LitulyVariant::weight)
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag)
    }

    companion object {
        private val DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(LitulyEntity::class.java, EntityDataSerializers.INT)

        fun setAttributes(): AttributeSupplier =
            createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_SPEED, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .build()
    }
}