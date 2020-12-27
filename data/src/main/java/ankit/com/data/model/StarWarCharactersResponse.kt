package ankit.com.data.model

import ankit.com.domain.model.CharactersDomainResponse
import com.google.gson.annotations.SerializedName

data class StarWarCharactersResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    @SerializedName("results")
    val starWarCharacters: List<StarWarCharacter>
)

fun StarWarCharactersResponse.toDomainCharacterResponse(): CharactersDomainResponse {
    return CharactersDomainResponse(
        count = this.count,
        next = this.next,
        previous = this.previous,
        modelCharacters = this.starWarCharacters.map { it.toDomainModel() }
    )
}