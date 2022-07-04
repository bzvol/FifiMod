package me.bzvol.fifimod.item

import net.minecraft.network.chat.TextComponent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class PetiItem(properties: Item.Properties) : Item(properties) {
    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        pPlayer.sendMessage(TextComponent("krumplipüré"), pPlayer.uuid)

        return super.use(pLevel, pPlayer, pUsedHand)
    }
}
