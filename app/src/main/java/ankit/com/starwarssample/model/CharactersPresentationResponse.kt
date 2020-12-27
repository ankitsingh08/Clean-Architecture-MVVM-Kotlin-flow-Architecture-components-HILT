package ankit.com.starwarssample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AnkitSingh on 12/16/20.
 */
data class CharactersPresentationResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val modelCharacters: List<CharacterPresentationModel>
)