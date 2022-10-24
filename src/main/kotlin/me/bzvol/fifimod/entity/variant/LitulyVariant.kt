package me.bzvol.fifimod.entity.variant

enum class LitulyVariant(val id: Int, val textureName: String, internal val weight: Int) {
    DEFAULT(0, "lituly", 60),
    RED(1, "lituly_red", 25),
    FIFI(2, "lituly_fifi", 5),
    OLD(3, "lituly_old", 10);

    companion object {
        private val BY_ID = values().associateBy(LitulyVariant::id)
        fun byId(id: Int) = BY_ID[id] ?: DEFAULT
    }
}