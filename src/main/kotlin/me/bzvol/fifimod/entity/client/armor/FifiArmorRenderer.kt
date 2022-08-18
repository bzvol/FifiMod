package me.bzvol.fifimod.entity.client.armor

import me.bzvol.fifimod.item.FifiArmorItem
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer

class FifiArmorRenderer : GeoArmorRenderer<FifiArmorItem>(FifiArmorModel()) {
    init {
        this.headBone = "armorHead"
        this.bodyBone = "armorBody"
        this.rightArmBone = "armorRightArm"
        this.leftArmBone = "armorLeftArm"
        this.rightLegBone = "armorLeftLeg"
        this.leftLegBone = "armorRightLeg"
        this.rightBootBone = "armorLeftBoot"
        this.leftBootBone = "armorRightBoot"
    }
}