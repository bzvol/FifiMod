package me.bzvol.fifimod.event.loot

import com.google.common.base.Suppliers
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import me.bzvol.fifimod.util.ModUtil.LOOT_FUNCTIONS_CODEC
import net.minecraft.Util
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Supplier

class MultipleOrItemAdditionModifier(
    conditionsIn: Array<out LootItemCondition>,
    private val additionList: List<Item>,
    private val chance: Float?,
    private val functions: Array<LootItemFunction>?
) : LootModifier(conditionsIn) {
    companion object {
        val CODEC: Supplier<Codec<MultipleOrItemAdditionModifier>> = Suppliers.memoize {
            RecordCodecBuilder.create { inst ->
                codecStart(inst)
                    .and(ForgeRegistries.ITEMS.codec.listOf().fieldOf("addition").forGetter { it.additionList })
                    .and(Codec.floatRange(0f, 1f).optionalFieldOf("chance", 1f).forGetter { it.chance ?: 1f })
                    .and(
                        LOOT_FUNCTIONS_CODEC.optionalFieldOf("functions", arrayOf())
                            .forGetter { it.functions ?: arrayOf() })
                    .apply(inst, ::MultipleOrItemAdditionModifier)
            }
        }
    }

    override fun doApply(generatedLoot: ObjectArrayList<ItemStack>, context: LootContext): ObjectArrayList<ItemStack> {
        val addition = Util.getRandom(additionList, context.random)
        val itemstack = ItemStack(addition)

        functions?.forEach { func ->
            func.apply(itemstack, context)
        }

        if (chance != null) {
            if (context.random.nextFloat() <= chance) generatedLoot.add(itemstack)
        } else generatedLoot.add(itemstack)

        return generatedLoot
    }

    override fun codec(): Codec<out IGlobalLootModifier> = CODEC.get()
}