@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER")

package me.bzvol.fifimod

import me.bzvol.fifimod.block.ModBlocks
import me.bzvol.fifimod.item.ModItems
import me.bzvol.fifimod.sound.ModSounds
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
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

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(FifiMod::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(FifiMod::onServerSetup)
                "test"
            })
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.FIFI_SPAWNER, RenderType.translucent())
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}