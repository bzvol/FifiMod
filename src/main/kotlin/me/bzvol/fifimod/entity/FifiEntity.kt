package me.bzvol.fifimod.entity

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.FifiSpawnerBlock
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.entity.ai.goal.PlaceSeedGoal
import me.bzvol.fifimod.item.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory

class FifiEntity(entityType: EntityType<out PathfinderMob>, level: Level) : PathfinderMob(entityType, level),
    IAnimatable {

    private var isSpawning = true
    private var rotAnim = 0
    private lateinit var spawnerPos: BlockPos

    private val factory: AnimationFactory = AnimationFactory(this)
    private val controller = AnimationController(this, "fifi_controller", 0f, this::predicate)

    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        if (this.isSpawning) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation("animation.fifi.spawn", false)
            )
            return PlayState.CONTINUE
        }

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

    override fun tick() {
        if (this.isSpawning && this.level.isClientSide) {
            ++this.rotAnim
            if (this::spawnerPos.isInitialized)
                this.setPos(spawnerPos.x.toDouble(), spawnerPos.y + 2.0, spawnerPos.z.toDouble())
            if (this.rotAnim >= 360) stopSpawning()
            FifiMod.LOGGER.debug("${this.uuid} is ${if (this.isSpawning) "" else "not "}spawning, rotAnim value is ${this.rotAnim}")
        }

    }

    fun startSpawning(pPos: BlockPos) {
        this.spawnerPos = pPos
        this.isSpawning = true
        // this.isNoAi = true
        this.isInvulnerable = true
    }

    private fun stopSpawning() {
        this.isSpawning = false
        // this.isNoAi = false
        this.isInvulnerable = false
        this.rotAnim = -360

        if (this::spawnerPos.isInitialized) {
            this.level.explode(
                null,
                spawnerPos.x.toDouble(), spawnerPos.y.toDouble(), spawnerPos.z.toDouble(),
                1f, false,
                Explosion.BlockInteraction.NONE
            )
            this.level.destroyBlock(spawnerPos, false)
        }
    }

    override fun getEatingSound(pStack: ItemStack): SoundEvent = SoundEvents.GENERIC_EAT

    companion object {
        fun setAttributes(): AttributeSupplier =
            createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_SPEED, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .build()
    }
}