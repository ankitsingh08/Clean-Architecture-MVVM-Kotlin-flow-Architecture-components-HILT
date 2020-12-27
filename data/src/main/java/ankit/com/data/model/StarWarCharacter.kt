package ankit.com.data.model

import ankit.com.domain.model.CharacterDomainModel

data class StarWarCharacter(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

fun StarWarCharacter.toDomainModel(): CharacterDomainModel {
    return CharacterDomainModel(
        birth_year = this.birth_year,
        created = this.created,
        edited = this.edited,
        eye_color = this.eye_color,
        films = this.films,
        gender = this.gender,
        hair_color = this.hair_color,
        height = this.height,
        homeworld = this.homeworld,
        mass = this.mass,
        name = this.name,
        skin_color = this.skin_color,
        species = this.species,
        starships = this.starships,
        url = this.url,
        vehicles = this.vehicles
    )
}