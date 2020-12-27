package ankit.com.starwarssample.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ankit.com.domain.core.successOr
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.model.PlanetDomainModel
import ankit.com.domain.model.SpeciesDomainModel
import ankit.com.domain.usecase.GetFilmsUseCase
import ankit.com.domain.usecase.GetPlanetUseCase
import ankit.com.domain.usecase.GetSpeciesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by AnkitSingh on 12/12/20.
 */
class CharacterDetailsViewModel @ViewModelInject constructor(
        private val getFilmsUseCase: GetFilmsUseCase,
        private val getPlanetUseCase: GetPlanetUseCase,
        private val getSpeciesUseCase: GetSpeciesUseCase
) : ViewModel() {

    private val _films = MutableLiveData<List<FilmsDomainModel>>()
    val films: LiveData<List<FilmsDomainModel>> = _films

    private val _species = MutableLiveData<SpeciesDomainModel>()
    val species: LiveData<SpeciesDomainModel> = _species

    private val _planet = MutableLiveData<PlanetDomainModel>()
    val planet: LiveData<PlanetDomainModel> = _planet

    fun getCharacterDetails(planetUrl: String, speciesUrl: String, filmUrl: String) {
        viewModelScope.launch {
            getPlanetDetails(planetUrl)
            getSpeciesDetails(speciesUrl)
            getFilmsDetails(filmUrl)
        }
    }

    private suspend fun getFilmsDetails(url: String) {
          getFilmsUseCase(url)
              .map { it.successOr(emptyList()) }
              .collect{
                  _films.value = it
              }
    }

    private suspend fun getSpeciesDetails(url: String) {
        getSpeciesUseCase(url)
            .map { it.successOr(null) }
            .collect{
                _species.value = it
            }

    }

    private suspend fun getPlanetDetails(url: String) {
        getPlanetUseCase(url)
            .map { it.successOr(null) }
            .collect{
                _planet.value = it
            }

    }


}