package ankit.com.domain.usecase
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.PlanetDomainModel
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/16/20.
 */
class GetPlanetUseCase @Inject constructor(
        private val repository: StarWarCharacterDetailRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<String, PlanetDomainModel>(ioDispatcher) {

    override fun execute(parameters: String): Flow<ApiResponse<PlanetDomainModel>> {
        return repository.getCharacterPlanetDetails(parameters)
    }
}