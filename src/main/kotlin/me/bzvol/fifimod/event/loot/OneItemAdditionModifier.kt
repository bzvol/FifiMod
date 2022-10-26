package me.bzvol.fifimod.event.loot

import com.google.common.base.Suppliers
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import me.bzvol.fifimod.util.ModUtil.LOOT_FUNCTIONS_CODEC
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries
import java.util.*
import java.util.function.Supplier

class OneItemAdditionModifier(
    conditionsIn: Array<out LootItemCondition>,
    private val addition: Item,
    private val chance: Float?,
    private val functions: Array<LootItemFunction>?
) :
    LootModifier(conditionsIn) {
    companion object {
        val CODEC: Supplier<Codec<OneItemAdditionModifier>> = Suppliers.memoize {
            RecordCodecBuilder.create { inst ->
                codecStart(inst)
                    .and(ForgeRegistries.ITEMS.codec.fieldOf("addition").forGetter { it.addition })
                    .and(Codec.floatRange(0f, 1f).optionalFieldOf("chance", 1f)
                        .forGetter { it.chance ?: 1f })
                    .and(LOOT_FUNCTIONS_CODEC.optionalFieldOf("functions", arrayOf())
                        .forGetter { it.functions ?: arrayOf() })
                    .apply(inst, ::OneItemAdditionModifier)
            }
        }
    }

    override fun codec(): Codec<out IGlobalLootModifier> = CODEC.get()

    override fun doApply(generatedLoot: ObjectArrayList<ItemStack>, context: LootContext): ObjectArrayList<ItemStack> {
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
}