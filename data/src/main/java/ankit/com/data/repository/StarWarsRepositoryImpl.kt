package ankit.com.data.repository

import ankit.com.data.model.toDomainCharacterResponse
import ankit.com.data.remote.StarWarsService
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.CharacterDomainModel
import ankit.com.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/12/20.
 */
class StarWarsRepositoryImpl @Inject constructor(private val starWarsService: StarWarsService)
    : StarWarsRepository {


    override fun getStarWarCharacter(characterName: String): Flow<ApiResponse<List<CharacterDomainModel>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = starWarsService.getMatchingCharacters(characterName).toDomainCharacterResponse()
                emit(ApiResponse.Success(response.modelCharacters))

            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }
    }
}