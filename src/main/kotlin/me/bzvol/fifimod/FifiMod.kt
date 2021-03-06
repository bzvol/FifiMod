@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER")

package me.bzvol.fifimod

import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.entity.ModEntityTypes
import me.bzvol.fifimod.entity.client.FifiRenderer
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.painting.ModPaintings
import me.bzvol.fifimod.sound.ModSounds
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
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

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(FifiMod::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(FifiMod::onServerSetup)
                "test"
            })

        GeckoLib.initialize()
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FIFI_SPAWNER, RenderType.translucent())
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FIFI_LEAVES, RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FIFHRANY, RenderType.cutout())

        EntityRenderers.register(ModEntityTypes.FIFI, ::FifiRenderer)
        EntityRenderers.register(ModEntityTypes.EFUM, ::ThrownItemRenderer)
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}