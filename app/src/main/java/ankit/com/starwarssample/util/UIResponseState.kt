package ankit.com.starwarssample.util

/**
 * Created by AnkitSingh on 12/12/20.
 */
sealed class UIResponseState<out T> {
    data class Success<out T>(val data: T) : UIResponseState<T>()
    data class Error(val exception: Exception) : UIResponseState<Nothing>()
    object Loading : UIResponseState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [CurrentState] is of type [Success] & holds non-null [Success.data].
 */
val UIResponseState<*>.succeeded
    get() = this is UIResponseState.Success && data != null

fun <T> UIResponseState<T>.successOr(fallback: T): T {
    return (this as? UIResponseState.Success<T>)?.data ?: fallback
}

val <T> UIResponseState<T>.data: T?
    get() = (this as? UIResponseState.Success)?.data