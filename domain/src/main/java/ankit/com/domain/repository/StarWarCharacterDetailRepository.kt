package ankit.com.domain.repository

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.model.PlanetDomainModel
import ankit.com.domain.model.SpeciesDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 12/16/20.
 */
interface StarWarCharacterDetailRepository{

    fun getCharacterSpeciesDetails(url: String): Flow<ApiResponse<SpeciesDomainModel>>

    fun getCharacterPlanetDetails(url: String): Flow<ApiResponse<PlanetDomainModel>>

    fun getCharacterFilmsDetails(url: String): Flow<ApiResponse<List<FilmsDomainModel>>>
}