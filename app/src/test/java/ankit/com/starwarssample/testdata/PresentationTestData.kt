package ankit.com.starwarssample.testdata

import ankit.com.domain.model.CharacterDomainModel
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.model.PlanetDomainModel
import ankit.com.domain.model.SpeciesDomainModel
import ankit.com.starwarssample.model.CharacterPresentationModel

/**
 * Created by AnkitSingh on 12/31/20.
 */
object PresentationTestData {

    internal fun getFilmsList(): List<FilmsDomainModel> {
        val film1 = FilmsDomainModel("A New Hope", "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base")
        val film2 = FilmsDomainModel("The Empire Strikes Back", "It is a dark time for the\\r\\nRebellion.")
        val filmList = mutableListOf<FilmsDomainModel>()
        filmList.add(film1)
        filmList.add(film2)
        return filmList
    }

    internal fun getSpeciesList(): List<SpeciesDomainModel> {
        val species1 = SpeciesDomainModel("https://swapi.dev/api/planets/9/", "Galactic Basic", "Human")
        val species2 = SpeciesDomainModel("https://swapi.dev/api/planets/14/", "Shyriiwook", "Wookie")
        val speciesList = mutableListOf<SpeciesDomainModel>()
        speciesList.add(species1)
        speciesList.add(species2)
        return speciesList
    }

    internal fun getStarWarCharactersList(): List<CharacterDomainModel> {
        val character1 = CharacterDomainModel(
            "19BBY",
            "2014-12-09T13:50:51.644000Z",
        "2014-12-20T21:17:56.891000Z",
            "blue",
            emptyList(),
            "male",
            "blond",
            "172",
            "https://swapi.dev/api/planets/1/",
            "77",
            "Luke Skywalker",
            "fair",
            emptyList(),
            emptyList(),
            "https://swapi.dev/api/people/1/",
            emptyList()
        )
        val character2 = CharacterDomainModel(
            "112BBY",
            "2014-12-10T15:10:51.357000Z",
            "2014-12-20T21:17:50.309000Z",
            "yellow",
            emptyList(),
            "n/a",
            "n/a",
            "167",
            "https://swapi.dev/api/planets/1/",
            "75",
            "C-3PO",
            "gold",
            emptyList(),
            emptyList(),
            "https://swapi.dev/api/people/2/",
            emptyList()
        )

        val charactersList = mutableListOf<CharacterDomainModel>()
        charactersList.add(character1)
        charactersList.add(character2)
        return charactersList
    }

    internal fun getPlanetDetails(): PlanetDomainModel {
        return PlanetDomainModel("Tatooine", "200000")
    }

    internal fun getPlanetUrl(): String = "https://swapi.dev/api/planets/1/"

    internal fun getFilmsUrl(): List<String> {
        val filmsList = mutableListOf<String>()
        filmsList.add("https://swapi.dev/api/films/1/")
        filmsList.add("https://swapi.dev/api/films/3/")
        return filmsList
    }

    internal fun getSpeciesUrl(): List<String> {
        val speciesList = mutableListOf<String>()
        speciesList.add("https://swapi.dev/api/species/2/")
        speciesList.add("https://swapi.dev/api/species/2/")
        return speciesList
    }
}