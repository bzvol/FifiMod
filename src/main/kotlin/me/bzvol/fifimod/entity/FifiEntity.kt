package me.bzvol.fifimod.entity

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.entity.ai.goal.PlaceSeedGoal
import me.bzvol.fifimod.item.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class FifiEntity(entityType: EntityType<out PathfinderMob>, level: Level) : PathfinderMob(entityType, level),
    IAnimatable {

    private val factory: AnimationFactory = AnimationFactory(this)
    private val controller = AnimationController(this, "controller", 0f, this::predicate)

    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation("animation.fifi.walk", true)
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
        this.goalSelector.addGoal(3, PlaceSeedGoal(this, ModBlocks.FIFHRANY.defaultBlockState(), 1.0))
        this.goalSelector.addGoal(4, TemptGoal(this, 1.1, Ingredient.of(ModItems.BOWL_OF_CCMPS), false))
        this.goalSelector.addGoal(5, LookAtPlayerGoal(this, Player::class.java, 8f))
        this.goalSelector.addGoal(6, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(7, RandomLookAroundGoal(this))
        this.goalSelector.addGoal(8, HurtByTargetGoal(this).setAlertOthers())
    }

    override fun mobInteract(pPlayer: Player, pHand: InteractionHand): InteractionResult {
        val itemstack = pPlayer.getItemInHand(pHand)
        if (itemstack.`is`(ModItems.BOWL_OF_CCMPS) && this.health < this.maxHealth) {
            this.eat(this.level, itemstack)
            this.heal(7f)
            this.controller.setAnimation(AnimationBuilder().addAnimation("animation.fifi.eye"))

            return InteractionResult.sidedSuccess(this.level.isClientSide)
        }

        return super.mobInteract(pPlayer, pHand)
    }

    override fun getEatingSound(pStack: ItemStack): SoundEvent = SoundEvents.GENERIC_EAT

    companion object {
        val SEEDING_AREA: EntityDataAccessor<List<BlockPos>> =
            SynchedEntityData.defineId(FifiEntity::class.java, object : EntityDataSerializer<List<BlockPos>> {
                override fun write(pBuffer: FriendlyByteBuf, pValue: List<BlockPos>) {
                    pBuffer.writeBlockPos(pValue[0])
                    pBuffer.writeBlockPos(pValue[1])
                }

                override fun read(pBuffer: FriendlyByteBuf): List<BlockPos> {
                    val positions = mutableListOf<BlockPos>()
                    positions.add(pBuffer.readBlockPos())
                    positions.add(pBuffer.readBlockPos())
                    return positions
                }

                override fun copy(pValue: List<BlockPos>): List<BlockPos> = pValue.toList()
            })

        fun setAttributes(): AttributeSupplier =
            createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_SPEED, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .build()
    }
}