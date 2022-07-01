package me.bzvol.fifimod.data

import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import java.util.function.Consumer

class ModRecipeProvider(generator: DataGenerator) : RecipeProvider(generator) {
    override fun buildCraftingRecipes(pFinishedRecipeConsumer: Consumer<FinishedRecipe>) {
        ShapedRecipeBuilder.shaped(ModBlocks.BISMUTH_BLOCK)
            .define('B', ModItems.BISMUTH)
            .define('G', Items.GOLD_NUGGET)
            .pattern("BBG")
            .pattern("BB ")
            .unlockedBy(
                "has_bismuth",
                has(ModItems.BISMUTH)
            )
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(ModItems.BISMUTH, 4)
            .requires(ModBlocks.BISMUTH_BLOCK)
            .unlockedBy(
                "has_bismuth_block",
                has(ModBlocks.BISMUTH_BLOCK)
            )
            .save(pFinishedRecipeConsumer)

        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(ModBlocks.BISMUTH_ORE), ModItems.BISMUTH,
            10f, 200
        )
            .unlockedBy(
                "has_bismuth_ore",
                has(ModBlocks.BISMUTH_ORE)
            )
            .save(pFinishedRecipeConsumer, ResourceLocation(FifiMod.MOD_ID, "bismuth_blasting"))

        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(ModBlocks.DEEPSLATE_BISMUTH_ORE), ModItems.BISMUTH,
            10f, 200
        )
            .unlockedBy(
                "has_deepslate_bismuth_ore",
                has(ModBlocks.DEEPSLATE_BISMUTH_ORE)
            )
            .save(pFinishedRecipeConsumer, ResourceLocation(FifiMod.MOD_ID, "bismuth_blasting_deepslate"))

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.POPPY), ModItems.POPPY_SEEDS,
            2f, 200
        )
            .unlockedBy(
                "has_poppy",
                has(Items.POPPY)
            )
            .save(pFinishedRecipeConsumer)

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.MILK_BUCKET), ModItems.COTTAGE_CHEESE,
            2f, 200
        )
            .unlockedBy(
                "has_milk_bucket",
                has(Items.MILK_BUCKET)
            )
            .save(pFinishedRecipeConsumer)

        ShapedRecipeBuilder.shaped(ModItems.BOWL_OF_CCMPS)
            .define('B', Items.BOWL)
            .define('M', Items.BROWN_MUSHROOM)
            .define('C', ModItems.COTTAGE_CHEESE)
            .define('P', ModItems.POPPY_SEEDS)
            .pattern(" P ")
            .pattern("CPM")
            .pattern(" B ")
            .unlockedBy(
                "has_ccmps_ingredients",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(ModItems.POPPY_SEEDS, ModItems.COTTAGE_CHEESE, Items.BROWN_MUSHROOM)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(ModBlocks.BURPBOX)
            .requires(Items.NOTE_BLOCK)
            .requires(Items.TNT)
            .unlockedBy(
                "has_burp_things",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(Items.NOTE_BLOCK, Items.TNT)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)
    }
}