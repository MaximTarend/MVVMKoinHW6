package by.hometrainng.mvvmkoin6.data.mapper

import by.hometrainng.mvvmkoin6.data.model.BreweryDTO
import by.hometrainng.mvvmkoin6.domain.model.Brewery

internal fun BreweryDTO.toDomainModel(): Brewery {
    return Brewery(
        id = id,
        name = name,
        longitude = longitude,
        latitude = latitude,
        websiteUrl = websiteUrl,
        phone = phone
    )
}

internal fun List<BreweryDTO>.toDomainModels(): List<Brewery> {
    return map { it.toDomainModel() }
}