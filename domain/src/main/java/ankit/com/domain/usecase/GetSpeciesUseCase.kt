package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.SpeciesDomainModel
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/16/20.
 */
class GetSpeciesUseCase @Inject constructor(
        private val repository: StarWarCharacterDetailRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
): BaseUseCase<List<String>, List<SpeciesDomainModel>>(ioDispatcher) {

    override fun execute(parameters: List<String>): Flow<ApiResponse<List<SpeciesDomainModel>>> {
        return repository.getCharacterSpeciesDetails(parameters)
    }
}