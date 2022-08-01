package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.event.loot.SquidRingsFromSquidAdditionModifier
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEventBusEvents {
    @SubscribeEvent
    fun registerModifierSerializers(event: RegistryEvent.Register<GlobalLootModifierSerializer<*>>) {
        event.registry.register(SquidRingsFromSquidAdditionModifier.Serializer().setRegistryName(
            ResourceLocation(FifiMod.MOD_ID, "squid_rings_from_squid")
        ))
    }
}