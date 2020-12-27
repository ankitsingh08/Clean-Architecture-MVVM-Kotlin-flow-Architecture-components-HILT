package ankit.com.domain.core

import kotlin.Result

/**
 * Created by AnkitSingh on 12/18/20.
 */
sealed class ApiResponse<out R> {

    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Exception) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [ApiResponse] is of type [Success] & holds non-null [Success.data].
 */
val ApiResponse<*>.succeeded
    get() = this is ApiResponse.Success && data != null

fun <T> ApiResponse<T>.successOr(fallback: T): T {
    return (this as? ApiResponse.Success<T>)?.data ?: fallback
}

val <T> ApiResponse<T>.data: T?
    get() = (this as? ApiResponse.Success)?.data