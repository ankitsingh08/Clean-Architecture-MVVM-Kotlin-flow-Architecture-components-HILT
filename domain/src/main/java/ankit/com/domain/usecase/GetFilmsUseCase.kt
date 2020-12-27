package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.di.IoDispatcher
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 12/16/20.
 */
class GetFilmsUseCase @Inject constructor(
        private val repository: StarWarCharacterDetailRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
) : BaseUseCase<String, List<FilmsDomainModel>>(ioDispatcher) {

    override fun execute(parameters: String): Flow<ApiResponse<List<FilmsDomainModel>>> {
        return repository.getCharacterFilmsDetails(parameters)
    }
}
