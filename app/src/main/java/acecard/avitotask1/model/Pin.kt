package acecard.avitotask1.model

data class Pin(
    val coordinates: Coordinates,
    val id: Int,
    val redundantField: String,
    val service: String
)