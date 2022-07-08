package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SimpleCookingSerializer
import net.minecraft.world.level.ItemLike
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

    companion object {
        private enum class CookingType(val pathName: String, val serializer: SimpleCookingSerializer<*>) {
            BLASTING("blasting", RecipeSerializer.BLASTING_RECIPE), SMOKING("smoking", RecipeSerializer.SMOKING_RECIPE)
        }
    }
}