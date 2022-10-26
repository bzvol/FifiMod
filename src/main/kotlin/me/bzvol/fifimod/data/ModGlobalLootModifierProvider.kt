package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.event.loot.MultipleOrItemAdditionModifier
import me.bzvol.fifimod.event.loot.OneItemAdditionModifier
import me.bzvol.fifimod.item.ModItems
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition

class ModGlobalLootModifierProvider(generator: DataGenerator) : GlobalLootModifierProvider(generator, FifiMod.MOD_ID) {
    override fun start() {
        this.add(
            "squid_rings_from_squid_modifier",
            OneItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("entities/squid")).build()),
                ModItems.SQUID_RINGS,
                0.7f,
                arrayOf(
                    SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build(),
                    ApplyBonusCount.addUniformBonusCount(Enchantments.MOB_LOOTING).build()
                )
            )
        )

        this.add(
            "beans_from_village_modifier",
            OneItemAdditionModifier(
                arrayOf(
                    LootTableIdCondition.builder(ResourceLocation("chests/village/village_plains_house")).or(
                        LootTableIdCondition.builder(
                            ResourceLocation("chests/village/village_taiga_house")
                        )
                    ).build()
                ),
                ModItems.BEAN,
                0.1f,
                arrayOf(
                    SetItemCountFunction.setCount(UniformGenerator.between(1f, 5f)).build()
                )
            )
        )

        /*
         * Uniqum swords:
         * Abandoned mineshaft:
         * - Exiled Sword
         * Jungle:
         * - Potatonium Sword
         * Shipwreck:
         * - Suspender
         * Igloo:
         * - Stormbringer
         * Ruined portal:
         * - Betrayer
         */

        this.add(
            "uniqum_swords_from_abandoned_mineshaft_modifier",
            MultipleOrItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/abandoned_mineshaft")).build()),
                listOf(
                    ModItems.EXILED_SWORD,
                ),
                0.03f,
                null
            )
        )
        this.add(
            "uniqum_swords_from_jungle_temple_modifier",
            MultipleOrItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/jungle_temple")).build()),
                listOf(
                    ModItems.POTATONIUM_SWORD,
                ),
                0.2f,
                null
            )
        )
        this.add(
            "uniqum_swords_from_shipwreck_modifier",
            MultipleOrItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/shipwreck_supply")).build()),
                listOf(
                    ModItems.SUSPENDER,
                ),
                0.1f,
                null
            )
        )
        this.add(
            "uniqum_swords_from_igloo_modifier",
            MultipleOrItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/igloo_chest")).build()),
                listOf(
                    ModItems.STORMBRINGER,
                ),
                0.3f,
                null
            )
        )
        this.add(
            "uniqum_swords_from_ruined_portal_modifier",
            MultipleOrItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/ruined_portal")).build()),
                listOf(
                    ModItems.BETRAYER,
                ),
                0.07f,
                null
            )
        )

        this.add(
            "fifi_sapling_from_desert_house",
            OneItemAdditionModifier(
                arrayOf(LootTableIdCondition.builder(ResourceLocation("chests/village/village_desert_house")).build()),
                ModBlocks.FIFI_SAPLING.asItem(),
                0.3f,
                arrayOf(
                    SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)).build()
                )
            )
        )
    }
}