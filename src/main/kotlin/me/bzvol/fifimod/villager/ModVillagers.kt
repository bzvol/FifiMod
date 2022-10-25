package me.bzvol.fifimod.villager

import com.google.common.collect.ImmutableSet
import me.bzvol.fifimod.FifiMod
import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.util.ObfuscationReflectionHelper
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModVillagers {
    private val POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, FifiMod.MOD_ID)
    private val PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FifiMod.MOD_ID)

    private val PETI_POI by POI_TYPES.registerObject("peti_poi") {
        PoiType(ModBlocks.POTATO_BLOCK.stateDefinition.possibleStates.toSet(), 1, 1)
    }

    val PETI_PROFESSION by PROFESSIONS.registerObject("peti") {
        VillagerProfession(
            "peti",
            { x -> x.get() == this.PETI_POI }, { x -> x.get() == this.PETI_POI },
            ImmutableSet.of(ModItems.PETI_ITEM), ImmutableSet.of(Blocks.FARMLAND),
            SoundEvents.VILLAGER_WORK_WEAPONSMITH
        )
    }

    fun registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(
                PoiType::class.java,
                "registerBlockStates", PoiType::class.java
            ).invoke(null, this.PETI_POI)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun register(eventBus: IEventBus) {
        POI_TYPES.register(eventBus)
        PROFESSIONS.register(eventBus)
    }
}