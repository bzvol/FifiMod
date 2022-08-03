package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.item.ModItems
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID)
object ModEvents {

    @SubscribeEvent
    fun createTrades(event: VillagerTradesEvent) {
        if (event.type == VillagerProfession.TOOLSMITH) {
            val trades = event.trades
            /*PETHINGS_OFFERS.forEach {
                val (cost, result, level) = it
                trades.get(level).add { _, _ ->
                    MerchantOffer(cost, result, 12, 2, 0.05f)
                }
            }*/
        }
        if (event.type == VillagerProfession.WEAPONSMITH) {
            event.trades.get(3).add { _, _ ->
                MerchantOffer(
                    ItemStack(Items.EMERALD, 30), ItemStack(ModItems.MODERN_BOW, 1),
                    3, 40, 0.2f
                )
            }
        }
    }
}