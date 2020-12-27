package ankit.com.domain.model
/**
 * Created by AnkitSingh on 12/16/20.
 */
data class CharactersDomainResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val modelCharacters: List<CharacterDomainModel>
)