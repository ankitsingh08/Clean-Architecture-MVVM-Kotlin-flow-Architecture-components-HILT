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
class StarWarsCharacterDetailsRepositoryImpl @Inject constructor(private val starWarsService: StarWarsService) :
    StarWarCharacterDetailRepository {

    override fun getCharacterSpeciesDetails(urls: List<String>): Flow<ApiResponse<List<SpeciesDomainModel>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val speciesList = mutableListOf<SpeciesDomainModel>()
                urls.forEach {
                    val species = starWarsService.getCharacterSpecies(it)
                    speciesList.add(species.toDomainModel())
                }
                emit(ApiResponse.Success(speciesList))
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

    override fun getCharacterFilmsDetails(urls: List<String>): Flow<ApiResponse<MutableList<FilmsDomainModel>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val filmsList = mutableListOf<FilmsDomainModel>()
                urls.forEach {
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