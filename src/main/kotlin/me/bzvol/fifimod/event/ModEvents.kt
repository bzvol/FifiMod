package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.event.loot.OneItemAdditionModifier
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEvents {
    @SubscribeEvent
    fun registerModifierSerializers(event: RegistryEvent.Register<GlobalLootModifierSerializer<*>>) {
        event.registry.register(OneItemAdditionModifier.Serializer().setRegistryName(
            ResourceLocation(FifiMod.MOD_ID, "one_item_modifier")
        ))
    }

    @SubscribeEvent
    fun entityAttributeEvent(event: EntityAttributeCreationEvent) {
        event.put(ModEntityTypes.FIFI, FifiEntity.setAttributes())
    }
}