package me.bzvol.fifimod.event.loot

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.minecraft.Util
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.Deserializers
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries

class MultipleOrItemAdditionModifier(
    conditionsIn: Array<out LootItemCondition>,
    private val additionList: Array<Item>,
    private val chance: Float?,
    private val functions: Array<LootItemFunction>?
) : LootModifier(conditionsIn) {

    override fun doApply(generatedLoot: MutableList<ItemStack>, context: LootContext): MutableList<ItemStack> {
        val addition = Util.getRandom(additionList, context.random)
        val itemstack = ItemStack(addition)

        functions?.forEach { func ->
            func.apply(itemstack, context)
        }

        if (chance != null) {
            if (context.random.nextFloat() <= chance)
                generatedLoot.add(itemstack)
        } else generatedLoot.add(itemstack)

        return generatedLoot
    }

    class Serializer : GlobalLootModifierSerializer<MultipleOrItemAdditionModifier>() {
        override fun read(
            location: ResourceLocation,
            `object`: JsonObject,
            conditionsIn: Array<out LootItemCondition>
        ): MultipleOrItemAdditionModifier {
            val additionsJson = GsonHelper.getAsJsonArray(`object`, "additions")
            val additions = additionsJson.map {
                GsonHelper.convertToItem(it, "addition")
            }.toTypedArray()

            val chance = if (`object`.has("chance"))
                `object`.getAsJsonPrimitive("chance").asFloat
            else null

            val functionsArray: JsonArray? = `object`.getAsJsonArray("functions")
            var functions: Array<LootItemFunction>? = null
            if (functionsArray != null) {
                functions = Deserializers.createFunctionSerializer().create()
                    .fromJson(functionsArray, Array<LootItemFunction>::class.java)
            }

            return MultipleOrItemAdditionModifier(conditionsIn, additions, chance, functions)
        }

        override fun write(instance: MultipleOrItemAdditionModifier): JsonObject {
            val json = makeConditions(instance.conditions)

            val additionsJson = JsonArray(instance.additionList.size)
            instance.additionList.forEach { item ->
                additionsJson.add(ForgeRegistries.ITEMS.getKey(item).toString())
            }

            json.add("additions", additionsJson)
            if (instance.chance != null)
                json.addProperty("chance", instance.chance)
            if (instance.functions != null)
                json.add(
                    "functions",
                    Deserializers.createFunctionSerializer().create().toJsonTree(instance.functions)
                )

            return json
        }

    }

}