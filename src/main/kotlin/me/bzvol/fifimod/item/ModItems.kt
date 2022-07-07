package me.bzvol.fifimod.item

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.sound.ModSounds
import me.bzvol.fifimod.util.ModCreativeModeTab
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.RecordItem
import net.minecraftforge.common.ForgeSpawnEggItem
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val REGISTRY: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, FifiMod.MOD_ID)

    val BISMUTH: Item by REGISTRY.registerObject("bismuth") { Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB)) }
    val POPPY_SEEDS: Item by REGISTRY.registerObject("poppy_seeds") { Item(Item.Properties().tab(CreativeModeTab.TAB_MISC)) }
    val COTTAGE_CHEESE: Item by REGISTRY.registerObject("cottage_cheese") { Item(Item.Properties().tab(CreativeModeTab.TAB_MISC)) }
    val PETI_ITEM: Item by REGISTRY.registerObject("peti_item") { PetiItem(Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)) }
    val BOWL_OF_CCMPS: Item by REGISTRY.registerObject("bowl_of_ccmps") { Item(Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BOWL_OF_CCMPS).stacksTo(1)) }
    val MUSIC_DISC_BIDIBODI: Item by REGISTRY.registerObject("music_disc_bidibodi") { RecordItem(15, ModSounds::MUSIC_DISC_BIDIBODI, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(1).rarity(Rarity.RARE)) }
    val AMETHYST_LIGHTER: Item by REGISTRY.registerObject("amethyst_lighter") { AmethystLighter(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(1)) }
    val THE_FIFHER: Item by REGISTRY.registerObject("the_fifher") { Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB)) }
    val FIFI_SPAWN_EGG: Item by REGISTRY.registerObject("fifi_spawn_egg") { ForgeSpawnEggItem(ModEntityTypes::FIFI, 0xba23b0, 0xf081d4, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB)) }
    val BEAN by REGISTRY.registerObject("bean") { BeanItem(Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BEAN)) }
    val EFUM by REGISTRY.registerObject("efum") { EfumItem(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(16)) }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}