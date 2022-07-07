package me.bzvol.fifimod.entity.projectile

import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.item.ModItems
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult

class Efum : ThrowableItemProjectile {

    constructor(pEntityType: EntityType<out Efum>, pLevel: Level) : super(pEntityType, pLevel)
    constructor(pLevel: Level, pShooter: LivingEntity) : super(ModEntityTypes.EFUM, pShooter, pLevel)
    constructor(pLevel: Level, pX: Double, pY: Double, pZ: Double) : super(ModEntityTypes.EFUM, pX, pY, pZ, pLevel)

    override fun getDefaultItem(): Item = ModItems.EFUM

    override fun onHitEntity(pResult: EntityHitResult) {
        super.onHitEntity(pResult)
        val entity = pResult.entity
        entity.hurt(DamageSource.thrown(this, this.owner), 3f)
    }

    override fun onHit(pResult: HitResult) {
        super.onHit(pResult)
        this.level.explode(null, this.x, this.y - 1.0, this.z, 5f, true, Explosion.BlockInteraction.DESTROY)
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, 3.toByte())
            this.discard()
        }
    }
}