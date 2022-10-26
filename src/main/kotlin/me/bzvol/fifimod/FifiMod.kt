@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER")

package me.bzvol.fifimod

import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.data.loot.ModGLMSerializers
import me.bzvol.fifimod.enchantment.ModEnchantments
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.entity.client.FifiRenderer
import me.bzvol.fifimod.entity.client.LitulyRenderer
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.painting.ModPaintings
import me.bzvol.fifimod.sound.ModSounds
import me.bzvol.fifimod.util.ModItemProperties
import me.bzvol.fifimod.villager.ModVillagers
import me.bzvol.fifimod.world.feature.ModOrePlacements
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import software.bernie.geckolib3.GeckoLib
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(FifiMod.MOD_ID)
object FifiMod {
    const val MOD_ID = "fifimod"

    val LOGGER: Logger = LogManager.getLogger(MOD_ID)

    init {
        ModBlocks.register(MOD_BUS)
        ModItems.register(MOD_BUS)
        ModSounds.register(MOD_BUS)
        ModEntityTypes.register(MOD_BUS)
        ModPaintings.register(MOD_BUS)
        ModGLMSerializers.register(MOD_BUS)
        ModEnchantments.register(MOD_BUS)
        ModVillagers.register(MOD_BUS)
        ModOrePlacements.register(MOD_BUS)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(FifiMod::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(FifiMod::onServerSetup)
                "test"
            })

        MOD_BUS.addListener(FifiMod::onSetup)

        GeckoLib.initialize()
    }

    private fun onSetup(event: FMLCommonSetupEvent) {
        ModVillagers.registerPOIs()

        SpawnPlacements.register(
            ModEntityTypes.LITULY,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            PathfinderMob::checkMobSpawnRules
        )
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")

        EntityRenderers.register(ModEntityTypes.FIFI, ::FifiRenderer)
        EntityRenderers.register(ModEntityTypes.LITULY, ::LitulyRenderer)
        EntityRenderers.register(ModEntityTypes.EFUM, ::ThrownItemRenderer)

        ModItemProperties.addItemProperties()
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}