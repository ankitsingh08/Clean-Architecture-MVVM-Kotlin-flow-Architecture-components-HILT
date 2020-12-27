package ankit.com.starwarssample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AnkitSingh on 12/16/20.
 */
@Parcelize
data class FilmsPresentationModel(
    val title: String,
    val openingCrawl: String
): Parcelable