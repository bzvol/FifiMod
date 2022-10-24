package me.bzvol.fifimod.gui

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import me.bzvol.fifimod.FifiMod
import net.minecraft.client.gui.chat.NarratorChatListener
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation

class FifiBookScreen : Screen(NarratorChatListener.NO_TITLE) {
    override fun render(pPoseStack: PoseStack, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        this.renderBackground(pPoseStack)
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.setShaderTexture(0, TEXTURE)

        val i = (this.width - 192) / 2
        this.blit(pPoseStack, i, 2, 0, 0, 192, 192)
    }

    companion object {
        private val TEXTURE = ResourceLocation(FifiMod.MOD_ID, "textures/gui/fifi_book.png")
    }
}