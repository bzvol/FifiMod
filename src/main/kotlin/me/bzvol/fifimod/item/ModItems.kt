package me.bzvol.fifimod.item

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.sound.ModSounds
import me.bzvol.fifimod.util.ModCreativeModeTab
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.*
import net.minecraft.world.item.crafting.RecipeType
import net.minecraftforge.common.ForgeSpawnEggItem
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

    val PETI_ITEM: Item by REGISTRY.registerObject("peti_item") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB).stacksTo(1))
    }

    val BOWL_OF_CCMPS: Item by REGISTRY.registerObject("bowl_of_ccmps") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BOWL_OF_CCMPS).stacksTo(1))
    }

    val MUSIC_DISC_BIDIBODI: Item by REGISTRY.registerObject("music_disc_bidibodi") {
        RecordItem(
            15,
            ModSounds::MUSIC_DISC_BIDIBODI,
            Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(1).rarity(Rarity.RARE)
        )
    }

    val AMETHYST_LIGHTER: Item by REGISTRY.registerObject("amethyst_lighter") {
        AmethystLighterItem(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(1))
    }

    val THE_FIFHER: Item by REGISTRY.registerObject("the_fifher") {
        Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    val FIFI_SPAWN_EGG: Item by REGISTRY.registerObject("fifi_spawn_egg") {
        ForgeSpawnEggItem(
            ModEntityTypes::FIFI,
            0xba23b0,
            0xf081d4,
            Item.Properties().tab(ModCreativeModeTab.FIFI_TAB)
        )
    }

    val BEAN by REGISTRY.registerObject("bean") {
        BeanItem(Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BEAN))
    }

    val EFUM by REGISTRY.registerObject("efum") {
        EfumItem(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).stacksTo(16))
    }

    val TIN_INGOT by REGISTRY.registerObject("tin_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val BRONZE_INGOT by REGISTRY.registerObject("bronze_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val BRONZE_PICKAXE by REGISTRY.registerObject("bronze_pickaxe") {
        PickaxeItem(ModTiers.BRONZE, 1, -2.8f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_AXE by REGISTRY.registerObject("bronze_axe") {
        AxeItem(ModTiers.BRONZE, 6f, -3.1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_SWORD by REGISTRY.registerObject("bronze_sword") {
        SwordItem(ModTiers.BRONZE, 3, -2.4f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_SHOVEL by REGISTRY.registerObject("bronze_shovel") {
        ShovelItem(ModTiers.BRONZE, 1.5f, -3f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_HOE by REGISTRY.registerObject("bronze_hoe") {
        HoeItem(ModTiers.BRONZE, -2, -1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_HELMET by REGISTRY.registerObject("bronze_helmet") {
        ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_CHESTPLATE by REGISTRY.registerObject("bronze_chestplate") {
        ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_LEGGINGS by REGISTRY.registerObject("bronze_leggings") {
        ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val BRONZE_BOOTS by REGISTRY.registerObject("bronze_boots") {
        ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }


    val RAW_STEEL by REGISTRY.registerObject("raw_steel") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val STEEL by REGISTRY.registerObject("steel") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val STEEL_PICKAXE by REGISTRY.registerObject("steel_pickaxe") {
        PickaxeItem(ModTiers.STEEL, 1, -2.8f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_AXE by REGISTRY.registerObject("steel_axe") {
        AxeItem(ModTiers.STEEL, 5f, -3.1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_SWORD by REGISTRY.registerObject("steel_sword") {
        SwordItem(ModTiers.STEEL, 3, -2.4f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_SHOVEL by REGISTRY.registerObject("steel_shovel") {
        ShovelItem(ModTiers.STEEL, 1.5f, -3f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_HOE by REGISTRY.registerObject("steel_hoe") {
        HoeItem(ModTiers.STEEL, -2, -1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_HELMET by REGISTRY.registerObject("steel_helmet") {
        ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_CHESTPLATE by REGISTRY.registerObject("steel_chestplate") {
        ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_LEGGINGS by REGISTRY.registerObject("steel_leggings") {
        ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val STEEL_BOOTS by REGISTRY.registerObject("steel_boots") {
        ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val FIFI by REGISTRY.registerObject("fifi") {
        Item(Item.Properties().tab(ModCreativeModeTab.FIFI_TAB).rarity(Rarity.RARE))
    }

    val OXIDIZED_COPPER_INGOT by REGISTRY.registerObject("oxidized_copper_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val PIG_IRON_INGOT by REGISTRY.registerObject("pig_iron_ingot") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val PIG_IRON_PICKAXE by REGISTRY.registerObject("pig_iron_pickaxe") {
        PickaxeItem(ModTiers.PIG_IRON, 1, -2.8f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_AXE by REGISTRY.registerObject("pig_iron_axe") {
        AxeItem(ModTiers.PIG_IRON, 5f, -3.1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_SWORD by REGISTRY.registerObject("pig_iron_sword") {
        SwordItem(ModTiers.PIG_IRON, 3, -2.4f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_SHOVEL by REGISTRY.registerObject("pig_iron_shovel") {
        ShovelItem(ModTiers.PIG_IRON, 1.5f, -3f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_HOE by REGISTRY.registerObject("pig_iron_hoe") {
        HoeItem(ModTiers.PIG_IRON, -2, -1f, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_HELMET by REGISTRY.registerObject("pig_iron_helmet") {
        ArmorItem(ModArmorMaterials.PIG_IRON, EquipmentSlot.HEAD, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_CHESTPLATE by REGISTRY.registerObject("pig_iron_chestplate") {
        ArmorItem(ModArmorMaterials.PIG_IRON, EquipmentSlot.CHEST, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_LEGGINGS by REGISTRY.registerObject("pig_iron_leggings") {
        ArmorItem(ModArmorMaterials.PIG_IRON, EquipmentSlot.LEGS, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }
    val PIG_IRON_BOOTS by REGISTRY.registerObject("pig_iron_boots") {
        ArmorItem(ModArmorMaterials.PIG_IRON, EquipmentSlot.FEET, Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB))
    }

    val FIFHRANY_SEEDS by REGISTRY.registerObject("fifhrany_seeds") {
        ItemNameBlockItem(ModBlocks.FIFHRANY, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    val FIFI_PICKAXE by REGISTRY.registerObject("fifi_pickaxe") {
        PickaxeItem(ModTiers.FIFI, 1, -2.8f, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }
    val FIFI_AXE by REGISTRY.registerObject("fifi_axe") {
        AxeItem(ModTiers.FIFI, 5f, -3.1f, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }
    val FIFI_SWORD by REGISTRY.registerObject("fifi_sword") {
        SwordItem(ModTiers.FIFI, 3, -2.4f, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }
    val FIFI_SHOVEL by REGISTRY.registerObject("fifi_shovel") {
        ShovelItem(ModTiers.FIFI, 1.5f, -3f, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }
    val FIFI_HOE by REGISTRY.registerObject("fifi_hoe") {
        HoeItem(ModTiers.FIFI, -2, -1f, Item.Properties().tab(ModCreativeModeTab.FIFI_TAB))
    }

    val SQUID_RINGS by REGISTRY.registerObject("squid_rings") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB).food(ModFoods.SQUID_RINGS))
    }
    val COOKED_SQUID_RINGS by REGISTRY.registerObject("cooked_squid_rings") {
        Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB).food(ModFoods.COOKED_SQUID_RINGS))
    }

    val ASH by REGISTRY.registerObject("ash") {
        object : Item(Item.Properties().tab(ModCreativeModeTab.PETHINGS_TAB)) {
            override fun getBurnTime(itemStack: ItemStack, recipeType: RecipeType<*>?): Int = 200
        }
    }

    fun register(eventBus: IEventBus) {
        REGISTRY.register(eventBus)
    }
}