package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.data.loot.ModGLMSerializers
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
            ModGLMSerializers.ONE_ITEM_MODIFIER,
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
            ModGLMSerializers.ONE_ITEM_MODIFIER,
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
    }
}