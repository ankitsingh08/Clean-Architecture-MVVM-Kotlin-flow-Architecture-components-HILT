package ankit.com.domain.usecase
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.CharacterDomainModel
import ankit.com.domain.repository.StarWarsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/12/20.
 */
class SearchCharactersUseCase @Inject constructor(
        private val repository: StarWarsRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
): BaseUseCase<String, List<CharacterDomainModel>>(ioDispatcher) {

    override fun execute(parameters: String): Flow<ApiResponse<List<CharacterDomainModel>>> {
        return repository.getStarWarCharacter(parameters)
    }
}