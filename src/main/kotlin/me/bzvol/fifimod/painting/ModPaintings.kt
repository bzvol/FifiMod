package me.bzvol.fifimod.painting

import me.bzvol.fifimod.FifiMod
import net.minecraft.world.entity.decoration.Motive
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModPaintings {
    val REGISTRY: DeferredRegister<Motive> = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, FifiMod.MOD_ID)

    val ROLCSIKAM by REGISTRY.registerObject("rolcsikam") {
        Motive(16, 32)
    }
    val PETIKEM by REGISTRY.registerObject("petikem") {
        Motive(16, 32)
    }
    val TUMBIKA by REGISTRY.registerObject("tumbika") {
        Motive(16, 16)
    }
    val SAD_CAT by REGISTRY.registerObject("sad_cat") {
        Motive(32, 32)
    }
    val POCOK by REGISTRY.registerObject("pocok") {
        Motive(32, 32)
    }
    val LORCSY_FREND by REGISTRY.registerObject("lorcsy_frend") {
        Motive(16, 48)
    }
    val PETKO by REGISTRY.registerObject("petko") {
        Motive(16, 16)
    }
    val FESZULET by REGISTRY.registerObject("feszulet") {
        Motive(32, 32)
    }
    val SHIBA_INU by REGISTRY.registerObject("shiba_inu") {
        Motive(32, 16)
    }
    val OLTONYOS_FRENDEK by REGISTRY.registerObject("oltonyos_frendek") {
        Motive(16, 32)
    }
    val OLTONYOS_GENG by REGISTRY.registerObject("oltonyos_geng") {
        Motive(32, 16)
    }
    val ROLCSY_SUNSHINE by REGISTRY.registerObject("rolcsy_sunshine") {
        Motive(16, 16)
    }


    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}