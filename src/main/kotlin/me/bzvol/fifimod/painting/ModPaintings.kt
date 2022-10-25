package me.bzvol.fifimod.painting

import me.bzvol.fifimod.FifiMod
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

@Suppress("unused")
object ModPaintings {
    val REGISTRY: DeferredRegister<PaintingVariant> =
        DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, FifiMod.MOD_ID)

    val ROLCSIKAM by REGISTRY.registerObject("rolcsikam") {
        PaintingVariant(16, 32)
    }
    val PETIKEM by REGISTRY.registerObject("petikem") {
        PaintingVariant(16, 32)
    }
    val TUMBIKA by REGISTRY.registerObject("tumbika") {
        PaintingVariant(16, 16)
    }
    val SAD_CAT by REGISTRY.registerObject("sad_cat") {
        PaintingVariant(32, 32)
    }
    val POCOK by REGISTRY.registerObject("pocok") {
        PaintingVariant(32, 32)
    }
    val LORCSY_FREND by REGISTRY.registerObject("lorcsy_frend") {
        PaintingVariant(16, 48)
    }
    val PETKO by REGISTRY.registerObject("petko") {
        PaintingVariant(16, 16)
    }
    val FESZULET by REGISTRY.registerObject("feszulet") {
        PaintingVariant(32, 32)
    }
    val SHIBA_INU by REGISTRY.registerObject("shiba_inu") {
        PaintingVariant(32, 16)
    }
    val OLTONYOS_FRENDEK by REGISTRY.registerObject("oltonyos_frendek") {
        PaintingVariant(16, 32)
    }
    val OLTONYOS_GENG by REGISTRY.registerObject("oltonyos_geng") {
        PaintingVariant(32, 16)
    }
    val ROLCSY_SUNSHINE by REGISTRY.registerObject("rolcsy_sunshine") {
        PaintingVariant(16, 16)
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}