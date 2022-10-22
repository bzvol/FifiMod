package me.bzvol.fifimod.util

import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

// Source: https://forums.minecraftforge.net/topic/74979-1144-rotate-voxel-shapes/
// Coded by @wyn_price
fun VoxelShape.rotate(from: Direction, to: Direction): VoxelShape {
    val buffer = arrayOf<VoxelShape>(this, Shapes.empty())

    val times: Int = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4
    for (i in 0 until times) {
        buffer[0].forAllBoxes { pMinX, pMinY, pMinZ, pMaxX, pMaxY, pMaxZ ->
            buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - pMaxZ, pMinY, pMinX, 1 - pMinZ, pMaxY, pMaxX))
        }
        buffer[0] = buffer[1]
        buffer[1] = Shapes.empty()
    }

    return buffer[0]
}