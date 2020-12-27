package ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Created by AnkitSingh on 12/25/20.
 */
/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [ApiResponse.Error] to the result) is the subclasses's responsibility.
 */
abstract class BaseUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<ApiResponse<R>> = execute(parameters)
            .catch { e -> emit(ApiResponse.Error(Exception(e))) }
            .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<ApiResponse<R>>
}