package me.bzvol.fifimod.event.loot

import com.google.gson.JsonObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries

class SquidRingsFromSquidAdditionModifier(conditionsIn: Array<out LootItemCondition>, private val addition: Item) :
    LootModifier(conditionsIn) {

    override fun doApply(generatedLoot: MutableList<ItemStack>, context: LootContext): MutableList<ItemStack> {
        val amount = context.random.nextInt(1, 3)
        generatedLoot.add(ItemStack(addition, amount))
        return generatedLoot
    }

    class Serializer : GlobalLootModifierSerializer<SquidRingsFromSquidAdditionModifier>() {
        override fun read(
            location: ResourceLocation,
            `object`: JsonObject,
            conditionsIn: Array<out LootItemCondition>
        ): SquidRingsFromSquidAdditionModifier {
            val addition = ForgeRegistries.ITEMS.getValue(
                ResourceLocation(GsonHelper.getAsString(`object`, "addition"))
            )
            return SquidRingsFromSquidAdditionModifier(conditionsIn, addition!!)
        }

        override fun write(instance: SquidRingsFromSquidAdditionModifier): JsonObject {
            val json = makeConditions(instance.conditions)
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString())
            return json
        }
    }
}