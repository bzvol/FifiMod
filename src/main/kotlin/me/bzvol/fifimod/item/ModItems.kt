package me.bzvol.fifimod.item

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.sound.ModSounds
import me.bzvol.fifimod.util.ModCreativeModeTab
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.RecordItem
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val REGISTRY: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, FifiMod.MOD_ID)

    val BISMUTH: Item by REGISTRY.registerObject("bismuth") {
        Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    val POPPY_SEEDS: Item by REGISTRY.registerObject("poppy_seeds") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_MISC))
    }

    val COTTAGE_CHEESE: Item by REGISTRY.registerObject("cottage_cheese") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_MISC))
    }

    val BOWL_OF_CCMPS: Item by REGISTRY.registerObject("bowl_of_ccmps") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BOWL_OF_CCMPS))
    }

    val MUSIC_DISC_BIDIBODI: Item by REGISTRY.registerObject("music_disc_bidibodi") {
        RecordItem(
            15,
            ModSounds::MUSIC_DISC_BIDIBODI,
            Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(1).rarity(Rarity.RARE)
        )
    }

    val AMETHYST_LIGHTER: Item by REGISTRY.registerObject("amethyst_lighter") {
        AmethystLighter(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    val THE_FIFHER: Item by REGISTRY.registerObject("the_fifher") {
        Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}