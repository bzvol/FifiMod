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

        cooking(Ingredient.of(ModBlocks.BISMUTH_ORE), ModItems.BISMUTH, 10f, CookingType.BLASTING)
            .save(pFinishedRecipeConsumer)

        cooking(Ingredient.of(ModBlocks.DEEPSLATE_BISMUTH_ORE), ModItems.BISMUTH, 10f, CookingType.BLASTING)
            .save(pFinishedRecipeConsumer)

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.POPPY), ModItems.POPPY_SEEDS, 2f, 200
        )
            .unlockedBy("has_poppy", has(Items.POPPY))
            .save(pFinishedRecipeConsumer)

        cooking(Ingredient.of(Items.MILK_BUCKET), ModItems.COTTAGE_CHEESE, 2f, CookingType.SMOKING)
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
                "has_burp_ingredients",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(Items.NOTE_BLOCK, Items.TNT)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(ModItems.AMETHYST_LIGHTER)
            .requires(Items.IRON_INGOT)
            .requires(Items.FLINT)
            .requires(Items.AMETHYST_SHARD)
            .unlockedBy(
                "has_amethyst_lighter_ingredients",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT, Items.FLINT, Items.AMETHYST_SHARD)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)

        ShapedRecipeBuilder.shaped(ModItems.THE_FIFHER)
            .define('A', Items.AMETHYST_SHARD)
            .pattern("AA")
            .pattern("AA")
            .pattern("A ")
            .unlockedBy(
                "has_amethyst_shard",
                has(Items.AMETHYST_SHARD)
            )
            .save(pFinishedRecipeConsumer)

        ShapedRecipeBuilder.shaped(ModBlocks.FIFI_SPAWNER)
            .define('S', ItemTags.STONE_CRAFTING_MATERIALS)
            .define('B', ModItems.BISMUTH)
            .define('F', ModItems.THE_FIFHER)
            .pattern(" F ")
            .pattern("SBS")
            .pattern("SSS")
            .unlockedBy(
                "has_fifi_spawner_ingredients",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(ModItems.BISMUTH, ModItems.THE_FIFHER)
                        .of(ItemTags.STONE_CRAFTING_MATERIALS)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(ModItems.PETI_ITEM)
            .requires(Items.POTATO)
            .requires(Items.STICK)
            .unlockedBy(
                "has_peti_ingredients",
                inventoryTrigger(
                    ItemPredicate.Builder.item()
                        .of(Items.POTATO, Items.STICK)
                        .build()
                )
            )
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(ModBlocks.POTATO_BLOCK)
            .requires(Items.POTATO, 9)
            .unlockedBy("has_potato", has(Items.POTATO))
            .save(pFinishedRecipeConsumer)

        ShapelessRecipeBuilder.shapeless(Items.POTATO, 9)
            .requires(ModBlocks.POTATO_BLOCK)
            .unlockedBy("has_potato_block", has(ModBlocks.POTATO_BLOCK))
            .save(pFinishedRecipeConsumer)
    }

    private fun cooking(
        pIngredient: Ingredient,
        pResult: ItemLike,
        pExperience: Float,
        pCookingType: CookingType
    ) = object {
        fun save(pFinishedRecipeConsumer: Consumer<FinishedRecipe>) {
            val resultName = pResult.asItem().registryName!!.path
            val ingredient = pIngredient.items[0].item
            val ingredientName = ingredient.registryName!!.path

            val advancementName = "has_$ingredientName"
            SimpleCookingRecipeBuilder.smelting(pIngredient, pResult, pExperience, 200)
                .unlockedBy(advancementName, has(ingredient))
                .save(
                    pFinishedRecipeConsumer, ResourceLocation(
                        FifiMod.MOD_ID,
                        "${resultName}_from_smelting_$ingredientName"
                    )
                )

            SimpleCookingRecipeBuilder.cooking(
                pIngredient, pResult, pExperience, 100, pCookingType.serializer
            )
                .unlockedBy(advancementName, has(ingredient))
                .save(
                    pFinishedRecipeConsumer, ResourceLocation(
                        FifiMod.MOD_ID,
                        "${resultName}_from_${pCookingType.pathName}_$ingredientName"
                    )
                )
        }
    }

    companion object {
        private enum class CookingType(val pathName: String, val serializer: SimpleCookingSerializer<*>) {
            BLASTING("blasting", RecipeSerializer.BLASTING_RECIPE),
            SMOKING("smoking", RecipeSerializer.SMOKING_RECIPE)
        }
    }
}