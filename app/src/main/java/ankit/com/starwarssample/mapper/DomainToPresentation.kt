package ankit.com.starwarssample.mapper

import ankit.com.domain.model.*
import ankit.com.starwarssample.model.*

/**
 * Created by AnkitSingh on 12/27/20.
 */

fun CharacterDomainModel.toPresentation(): CharacterPresentationModel {
    return CharacterPresentationModel(
        birth_year,
        created,
        edited,
        eye_color,
        films,
        gender,
        hair_color,
        height,
        homeworld,
        mass,
        name,
        skin_color,
        species,
        starships,
        url,
        vehicles
    )
}

//fun CharactersDomainResponse.toPresentation(): CharactersPresentationResponse {
//    return CharactersPresentationResponse(
//        count,
//    next,
//    previous,
//    modelCharacters
//    )
//}

fun FilmsDomainModel.toPresentation(): FilmsPresentationModel {
    return FilmsPresentationModel(
        title,
        openingCrawl
    )
}

fun PlanetDomainModel.toPresentation(): PlanetPresentationModel {
    return PlanetPresentationModel(
        name,
        population
    )
}

fun SpeciesDomainModel.toPresentation(): SpeciesPresentationModel {
    return SpeciesPresentationModel(
        homeworld,
        language,
        name
    )
}

fun List<CharacterDomainModel>.toPresentationCharacterList(): List<CharacterPresentationModel> {
    return this.map {
        it.toPresentation()
    }
}

fun List<SpeciesDomainModel>.toPresentationSpeciesList(): List<SpeciesPresentationModel> {
    return this.map {
        it.toPresentation()
    }
}

fun List<FilmsDomainModel>.toPresentationFilmsList(): List<FilmsPresentationModel> {
    return this.map {
        it.toPresentation()
    }
}
