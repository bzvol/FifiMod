package me.bzvol.fifimod.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorMaterial
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.item.GeoArmorItem

class FifiArmorItem(pMaterial: ArmorMaterial, pSlot: EquipmentSlot, pBuilder: Properties) :
    GeoArmorItem(pMaterial, pSlot, pBuilder), IAnimatable {
    private val factory: AnimationFactory = AnimationFactory(this)
    private val controller =
        AnimationController(this, "fifi_armor_controller", 20f, this::predicate)

    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(this.controller)
    }

    override fun getFactory(): AnimationFactory = this.factory
}