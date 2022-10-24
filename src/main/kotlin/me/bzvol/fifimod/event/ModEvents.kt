package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.FifiEntity
import me.bzvol.fifimod.entity.LitulyEntity
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.event.loot.MultipleOrItemAdditionModifier
import me.bzvol.fifimod.event.loot.OneItemAdditionModifier
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegisterEvent

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModEvents {
    @SubscribeEvent
    fun registerModifierSerializers(event: RegisterEvent) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS) { helper ->
            helper.register(
                ResourceLocation(FifiMod.MOD_ID, "one_item_modifier"),
                OneItemAdditionModifier.CODEC.get()
            )
            helper.register(
                ResourceLocation(FifiMod.MOD_ID, "multiple_or_item_modifier"),
                MultipleOrItemAdditionModifier.CODEC.get()
            )
        }
    }

    @SubscribeEvent
    fun entityAttributeEvent(event: EntityAttributeCreationEvent) {
        event.put(ModEntityTypes.FIFI, FifiEntity.setAttributes())
        event.put(ModEntityTypes.LITULY, LitulyEntity.setAttributes())
    }
}