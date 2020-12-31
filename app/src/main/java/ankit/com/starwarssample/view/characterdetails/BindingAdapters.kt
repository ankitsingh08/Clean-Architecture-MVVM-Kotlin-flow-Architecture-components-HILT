package ankit.com.starwarssample.view.characterdetails

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ankit.com.domain.core.ApiResponse

/**
 * Created by AnkitSingh on 12/29/20.
 */
@BindingAdapter("hideOnLoading")
fun ViewGroup.hideOnLoading(responseState: ApiResponse<Nothing>) {
    visibility = if (responseState is ApiResponse.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("showOnLoading")
fun ProgressBar.showOnLoading(responseState: ApiResponse<Nothing>) {
    visibility = if (responseState is ApiResponse.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("showOnError")
fun TextView.showError(responseState: ApiResponse<Nothing>) {
    visibility = if (responseState is ApiResponse.Error)
        View.VISIBLE
    else
        View.GONE
    text = (responseState as ApiResponse.Error).exception.message
}
