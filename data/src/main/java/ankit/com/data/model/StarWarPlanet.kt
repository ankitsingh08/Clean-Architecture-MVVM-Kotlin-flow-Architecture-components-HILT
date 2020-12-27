package ankit.com.data.model

import ankit.com.domain.model.PlanetDomainModel

data class StarWarPlanet(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String
)

fun StarWarPlanet.toDomainModel(): PlanetDomainModel {
    return PlanetDomainModel(
        name = this.name,
        population = this.population
    )
}