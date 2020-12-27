package ankit.com.data.remote

import ankit.com.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by AnkitSingh on 12/12/20.
 */
interface StarWarsService {

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }

    @GET("people")
    suspend fun getMatchingCharacters(@Query("search") characterName: String): StarWarCharactersResponse

    @GET
    suspend fun getCharacterSpecies(@Url url: String): StarWarSpecies

    @GET
    suspend fun getCharacterPlanet(@Url url: String): StarWarPlanet

    @GET
    suspend fun getFilmsList(@Url url: String): StarWarFilms

    @GET
    suspend fun getFilmDetails(@Url url: String): StarWarFilmsDetails
}