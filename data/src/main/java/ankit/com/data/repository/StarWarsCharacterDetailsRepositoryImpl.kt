package ankit.com.data.repository

import ankit.com.data.model.toDomainModel
import ankit.com.data.remote.StarWarsService
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.model.PlanetDomainModel
import ankit.com.domain.model.SpeciesDomainModel
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/16/20.
 */
class StarWarsCharacterDetailsRepositoryImpl @Inject constructor(private val starWarsService: StarWarsService)
    : StarWarCharacterDetailRepository {

    override fun getCharacterSpeciesDetails(url: String): Flow<ApiResponse<SpeciesDomainModel>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = starWarsService.getCharacterSpecies(url)
                emit(ApiResponse.Success(response.toDomainModel()))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }

    override fun getCharacterPlanetDetails(url: String): Flow<ApiResponse<PlanetDomainModel>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = starWarsService.getCharacterPlanet(url)
                emit(ApiResponse.Success(response.toDomainModel()))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }

    override fun getCharacterFilmsDetails(url: String): Flow<ApiResponse<MutableList<FilmsDomainModel>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = starWarsService.getFilmsList(url)
                val filmsList = mutableListOf<FilmsDomainModel>()
                response.filmUrls.forEach {
                    val film = starWarsService.getFilmDetails(it)
                    filmsList.add(film.toDomainModel())
                }

                emit(ApiResponse.Success(filmsList))
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }

}