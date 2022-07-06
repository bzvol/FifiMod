package me.bzvol.fifimod.entity.projectile

import me.bzvol.fifimod.entity.ModEntityTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level

class Efum(pLevel: Level, pShooter: LivingEntity) : ThrowableItemProjectile(ModEntityTypes.EFUM, pShooter, pLevel) {
    constructor(pLevel: Level, pX: Double, pY: Double, pZ: Double) : super(ModEntityTypes.EFUM, pX, pY, pZ, pLevel)

    override fun getDefaultItem(): Item {

    }
}