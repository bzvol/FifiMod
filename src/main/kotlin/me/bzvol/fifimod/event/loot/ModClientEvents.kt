package me.bzvol.fifimod.event.loot

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.client.armor.FifiArmorRenderer
import me.bzvol.fifimod.item.FifiArmorItem
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object ModClientEvents {
    @SubscribeEvent
    fun registerArmorRenderers(event: EntityRenderersEvent.AddLayers) {
        GeoArmorRenderer.registerArmorRenderer(FifiArmorItem::class.java, FifiArmorRenderer())
    }
}