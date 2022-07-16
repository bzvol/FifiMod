package me.bzvol.fifimod.item

import me.bzvol.fifimod.FifiMod
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient

enum class ModArmorMaterials(
    private val materialName: String,
    private val durabilityMultiplier: Int,
    private val slotProtections: List<Int>,
    private val enchantmentValue: Int,
    private val sound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredient: () -> Ingredient
) : ArmorMaterial {
    BRONZE("bronze", 15, listOf(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, {
        Ingredient.of(ModItems.BRONZE_INGOT)
    }),
    STEEL("steel", 15, listOf(2, 5, 6, 2), 9, SoundEvents.ARMOR_EQUIP_IRON, 0f, 0f, {
        Ingredient.of(ModItems.STEEL)
    }),
    FIFI("fifi", 45, listOf(3, 6, 8, 3), 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.3f, {
        Ingredient.of(ModItems.FIFI)
    }),
    PIG_IRON("pig_iron", 33, listOf(3, 6, 8, 3), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2f, 0f, {
        Ingredient.of(ModItems.PIG_IRON_INGOT)
    });

    override fun getDurabilityForSlot(pSlot: EquipmentSlot): Int =
        HEALTH_PER_SLOT[pSlot.index] * this.durabilityMultiplier
    override fun getDefenseForSlot(pSlot: EquipmentSlot): Int = this.slotProtections[pSlot.index]
    override fun getEnchantmentValue(): Int = this.enchantmentValue
    override fun getEquipSound(): SoundEvent = this.sound
    override fun getRepairIngredient(): Ingredient = this.repairIngredient.invoke()
    override fun getName(): String = "${FifiMod.MOD_ID}:${this.materialName}"
    override fun getToughness(): Float = this.toughness
    override fun getKnockbackResistance(): Float = this.knockbackResistance

    companion object {
        val HEALTH_PER_SLOT: List<Int> = listOf(13, 15, 16, 11)
    }
}