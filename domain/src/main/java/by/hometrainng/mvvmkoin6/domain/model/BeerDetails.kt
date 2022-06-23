package by.hometrainng.mvvmkoin6.domain.model

data class BeerDetails(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageURL: String?,
    val foodPairing: List<String>,
    val brewersTips: String
)