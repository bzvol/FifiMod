package me.bzvol.fifimod.event

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.villager.ModVillagers
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = FifiMod.MOD_ID)
object ModForgeEvents {
    @SubscribeEvent
    fun createTrades(event: VillagerTradesEvent) {
        val trades = event.trades
        if (event.type == ModVillagers.PETI_PROFESSION) {
            trades.get(1).addAll(arrayOf(
                ItemListing { _, _ ->
                    MerchantOffer(
                        ItemStack(ModItems.BRONZE_INGOT, 6), ItemStack(Items.EMERALD),
                        12, 3, 0.02f
                    )
                },
                ItemListing { _, _ ->
                    MerchantOffer(
                        ItemStack(ModItems.STEEL, 3), ItemStack(Items.EMERALD),
                        12, 3, 0.02f
                    )
                },
                ItemListing { _, _ ->
                    MerchantOffer(
                        ItemStack(ModItems.PIG_IRON_INGOT), ItemStack(Items.EMERALD),
                        12, 3, 0.02f
                    )
                },
            ))
            trades.get(2).add { _, _ ->
                MerchantOffer(
                    ItemStack(Items.EMERALD, 30), ItemStack(ModItems.MODERN_BOW, 1),
                    3, 40, 0.2f
                )
            }
        }
    }
}