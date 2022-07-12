package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.util.ModTags
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SimpleCookingSerializer
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer

class ModRecipeProvider(generator: DataGenerator) : RecipeProvider(generator) {
    override fun buildCraftingRecipes(pFinishedRecipeConsumer: Consumer<FinishedRecipe>) {
        ShapedRecipeBuilder.shaped(ModBlocks.BISMUTH_BLOCK).define('B', ModItems.BISMUTH).define('G', Items.GOLD_NUGGET).pattern("BBG").pattern("BB ").unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.BISMUTH, 4).requires(ModBlocks.BISMUTH_BLOCK).unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(pFinishedRecipeConsumer)
        cooking(Ingredient.of(ModBlocks.BISMUTH_ORE), ModItems.BISMUTH, 10f, CookingType.BLASTING).save(pFinishedRecipeConsumer)
        cooking(Ingredient.of(ModBlocks.DEEPSLATE_BISMUTH_ORE), ModItems.BISMUTH, 10f, CookingType.BLASTING).save(pFinishedRecipeConsumer)
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.POPPY), ModItems.POPPY_SEEDS, 2f, 200).unlockedBy("has_poppy", has(Items.POPPY)).save(pFinishedRecipeConsumer)
        cooking(Ingredient.of(Items.MILK_BUCKET), ModItems.COTTAGE_CHEESE, 2f, CookingType.SMOKING).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BOWL_OF_CCMPS).define('B', Items.BOWL).define('M', Items.BROWN_MUSHROOM).define('C', ModItems.COTTAGE_CHEESE).define('P', ModItems.POPPY_SEEDS).pattern(" P ").pattern("CPM").pattern(" B ").unlockedBy("has_brown_mushroom", has(Items.BROWN_MUSHROOM)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModBlocks.BURPBOX).requires(Items.NOTE_BLOCK).requires(Items.TNT).unlockedBy("has_note_block", has(Items.NOTE_BLOCK)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.AMETHYST_LIGHTER).requires(Items.IRON_INGOT).requires(Items.FLINT).requires(Items.AMETHYST_SHARD).unlockedBy("has_flint", has(Items.FLINT)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.THE_FIFHER).define('A', Items.AMETHYST_SHARD).pattern("AA").pattern("AA").pattern("A ").unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_SPAWNER).define('S', ItemTags.STONE_CRAFTING_MATERIALS).define('B', ModItems.BISMUTH).define('F', ModItems.THE_FIFHER).pattern(" F ").pattern("SBS").pattern("SSS").unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.PETI_ITEM).requires(Items.POTATO).requires(Items.STICK).unlockedBy("has_potato", has(Items.POTATO)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModBlocks.POTATO_BLOCK).requires(Items.POTATO, 9).unlockedBy("has_potato", has(Items.POTATO)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(Items.POTATO, 9).requires(ModBlocks.POTATO_BLOCK).unlockedBy("has_potato_block", has(ModBlocks.POTATO_BLOCK)).save(pFinishedRecipeConsumer)
        val chainmailArmorTrigger = inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT, Items.IRON_NUGGET).build())
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_HELMET).define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET).pattern("III").pattern("N N").unlockedBy("has_chainmail_helmet_ingredients", chainmailArmorTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_CHESTPLATE).define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET).pattern("N N").pattern("III").pattern("NNN").unlockedBy("has_chainmail_chestplate_ingredients", chainmailArmorTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_LEGGINGS).define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET).pattern("NIN").pattern("I I").pattern("N N").unlockedBy("has_chainmail_leggings_ingredients", chainmailArmorTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(Items.CHAINMAIL_BOOTS).define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET).pattern("N N").pattern("I I").unlockedBy("has_chainmail_boots_ingredients", chainmailArmorTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.EFUM).define('R', Items.REDSTONE).define('B', Items.BLAZE_POWDER).define('G', Items.GUNPOWDER).pattern(" B ").pattern("RGR").pattern(" R ").unlockedBy("has_blaze_powder", has(Items.BLAZE_POWDER))
        ShapelessRecipeBuilder.shapeless(ModBlocks.TIN_BLOCK).requires(ModItems.TIN_INGOT, 9).unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.TIN_INGOT, 9).requires(ModBlocks.TIN_BLOCK).unlockedBy("has_tin_block", has(ModBlocks.TIN_BLOCK)).save(pFinishedRecipeConsumer)
        cooking(Ingredient.of(ModBlocks.TIN_ORE), ModItems.TIN_INGOT, 4f, CookingType.BLASTING).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModBlocks.BRONZE_BLOCK).requires(ModItems.BRONZE_INGOT, 9).unlockedBy("has_bronze_ingot", has(ModItems.BRONZE_INGOT)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_INGOT, 9).requires(ModBlocks.BRONZE_BLOCK).unlockedBy("has_bronze_block", has(ModBlocks.BRONZE_BLOCK)).save(pFinishedRecipeConsumer, ResourceLocation(FifiMod.MOD_ID, "bronze_ingot_from_bronze_block"))
        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_INGOT, 2).requires(ModItems.TIN_INGOT).requires(Items.COPPER_INGOT).unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.RAW_STEEL).define('I', Items.IRON_INGOT).define('C', Items.COAL).pattern("CCC").pattern("CIC").pattern("CCC").unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pFinishedRecipeConsumer)
        cooking(Ingredient.of(ModItems.RAW_STEEL), ModItems.STEEL, 2f, CookingType.BLASTING).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModBlocks.STEEL_BLOCK).requires(ModItems.STEEL, 9).unlockedBy("has_steel", has(ModItems.STEEL)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.STEEL, 9).requires(ModBlocks.STEEL_BLOCK).unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(pFinishedRecipeConsumer)
        val bronzeTrigger = has(ModItems.BRONZE_INGOT)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_PICKAXE).define('B', ModItems.BRONZE_INGOT).define('S', Items.STICK).pattern("BBB").pattern(" S ").pattern(" S ").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_AXE).define('B', ModItems.BRONZE_INGOT).define('S', Items.STICK).pattern("BB ").pattern("BS ").pattern(" S ").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SWORD).define('B', ModItems.BRONZE_INGOT).define('S', Items.STICK).pattern(" B ").pattern(" B ").pattern(" S ").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SHOVEL).define('B', ModItems.BRONZE_INGOT).define('S', Items.STICK).pattern(" B ").pattern(" S ").pattern(" S ").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HOE).define('B', ModItems.BRONZE_INGOT).define('S', Items.STICK).pattern("BB ").pattern(" S ").pattern(" S ").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_HELMET).define('B', ModItems.BRONZE_INGOT).pattern("BBB").pattern("B B").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_CHESTPLATE).define('B', ModItems.BRONZE_INGOT).pattern("B B").pattern("BBB").pattern("BBB").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_LEGGINGS).define('B', ModItems.BRONZE_INGOT).pattern("BBB").pattern("B B").pattern("B B").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_BOOTS).define('B', ModItems.BRONZE_INGOT).pattern("B B").pattern("B B").unlockedBy("has_bronze_ingot", bronzeTrigger).save(pFinishedRecipeConsumer)
        val steelTrigger = has(ModItems.STEEL)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_PICKAXE).define('T', ModItems.STEEL).define('S', Items.STICK).pattern("TTT").pattern(" S ").pattern(" S ").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_AXE).define('T', ModItems.STEEL).define('S', Items.STICK).pattern("TT ").pattern("TS ").pattern(" S ").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_SWORD).define('T', ModItems.STEEL).define('S', Items.STICK).pattern(" T ").pattern(" T ").pattern(" S ").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_SHOVEL).define('T', ModItems.STEEL).define('S', Items.STICK).pattern(" T ").pattern(" S ").pattern(" S ").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_HOE).define('T', ModItems.STEEL).define('S', Items.STICK).pattern("TT ").pattern(" S ").pattern(" S ").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_HELMET).define('S', ModItems.STEEL).pattern("SSS").pattern("S S").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_CHESTPLATE).define('S', ModItems.STEEL).pattern("S S").pattern("SSS").pattern("SSS").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_LEGGINGS).define('S', ModItems.STEEL).pattern("SSS").pattern("S S").pattern("S S").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModItems.STEEL_BOOTS).define('S', ModItems.STEEL).pattern("S S").pattern("S S").unlockedBy("has_steel", steelTrigger).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModBlocks.FIFI_PLANKS, 4).requires(ModTags.Items.FIFI_LOGS).unlockedBy("has_fifi_log", has(ModTags.Items.FIFI_LOGS)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_WOOD, 3).define('#', ModBlocks.FIFI_LOG).pattern("##").pattern("##").unlockedBy("has_fifi_log", has(ModBlocks.FIFI_LOG)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.STRIPPED_FIFI_WOOD, 3).define('#', ModBlocks.STRIPPED_FIFI_LOG).pattern("##").pattern("##").unlockedBy("has_stripped_fifi_log", has(ModBlocks.STRIPPED_FIFI_LOG)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_STAIRS, 4).define('#', ModBlocks.FIFI_PLANKS).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_fifi_planks", has(ModBlocks.FIFI_PLANKS)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_SLAB, 6).define('#', ModBlocks.FIFI_PLANKS).pattern("###").unlockedBy("has_fifi_planks", has(ModBlocks.FIFI_PLANKS)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_FENCE, 3).define('#', ModBlocks.FIFI_PLANKS).define('S', Items.STICK).pattern("#S#").pattern("#S#").unlockedBy("has_fifi_planks", has(ModBlocks.FIFI_PLANKS)).save(pFinishedRecipeConsumer)
        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_FENCE_GATE).define('#', ModBlocks.FIFI_PLANKS).define('S', Items.STICK).pattern("S#S").pattern("S#S").unlockedBy("has_fifi_planks", has(ModBlocks.FIFI_PLANKS)).save(pFinishedRecipeConsumer)
        ShapelessRecipeBuilder.shapeless(ModItems.OXIDIZED_COPPER_INGOT, 9).requires(Blocks.OXIDIZED_COPPER).unlockedBy("has_oxidized_copper", has(Blocks.OXIDIZED_COPPER)).save(pFinishedRecipeConsumer)
    }

    private fun cooking(
        pIngredient: Ingredient, pResult: ItemLike, pExperience: Float, pCookingType: CookingType
    ) = object {
        fun save(pFinishedRecipeConsumer: Consumer<FinishedRecipe>) {
            val resultName = pResult.asItem().registryName!!.path
            val ingredient = pIngredient.items[0].item
            val ingredientName = ingredient.registryName!!.path

            val advancementName = "has_$ingredientName"
            SimpleCookingRecipeBuilder.smelting(pIngredient, pResult, pExperience, 200)
                .unlockedBy(advancementName, has(ingredient)).save(
                    pFinishedRecipeConsumer, ResourceLocation(
                        FifiMod.MOD_ID, "${resultName}_from_smelting_$ingredientName"
                    )
                )

            SimpleCookingRecipeBuilder.cooking(
                pIngredient, pResult, pExperience, 100, pCookingType.serializer
            ).unlockedBy(advancementName, has(ingredient)).save(
                pFinishedRecipeConsumer, ResourceLocation(
                    FifiMod.MOD_ID, "${resultName}_from_${pCookingType.pathName}_$ingredientName"
                )
            )
        }
    }

    private fun has(pTag: TagKey<Item>): InventoryChangeTrigger.TriggerInstance =
        inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build())

    companion object {
        private enum class CookingType(val pathName: String, val serializer: SimpleCookingSerializer<*>) {
            BLASTING("blasting", RecipeSerializer.BLASTING_RECIPE), SMOKING("smoking", RecipeSerializer.SMOKING_RECIPE)
        }
    }
}