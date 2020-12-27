package ankit.com.data.model

import ankit.com.domain.model.FilmsDomainModel

data class StarWarFilms(val filmUrls: List<String>)

data class StarWarFilmsDetails(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)

fun StarWarFilmsDetails.toDomainModel(): FilmsDomainModel {
    return FilmsDomainModel(
        title = this.title,
        openingCrawl = this.opening_crawl
    )
}