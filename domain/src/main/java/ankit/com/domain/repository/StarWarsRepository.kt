package ankit.com.domain.repository

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 12/12/20.
 */
interface StarWarsRepository {
    fun getStarWarCharacter(characterName: String): Flow<ApiResponse<List<CharacterDomainModel>>>
}