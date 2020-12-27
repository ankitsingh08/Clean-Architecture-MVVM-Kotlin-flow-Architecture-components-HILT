package ankit.com.starwarssample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AnkitSingh on 12/16/20.
 */
@Parcelize
data class SpeciesPresentationModel(
    val homeworld: String,
    val language: String,
    val name: String
): Parcelable