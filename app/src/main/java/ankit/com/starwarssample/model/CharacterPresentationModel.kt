package ankit.com.starwarssample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AnkitSingh on 12/16/20.
 */
@Parcelize
data class CharacterPresentationModel(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
): Parcelable