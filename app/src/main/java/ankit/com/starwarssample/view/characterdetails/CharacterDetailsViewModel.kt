package ankit.com.starwarssample.view.characterdetails

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
import ankit.com.starwarssample.mapper.toPresentation
import ankit.com.starwarssample.mapper.toPresentationFilmsList
import ankit.com.starwarssample.mapper.toPresentationSpeciesList
import ankit.com.starwarssample.model.FilmsPresentationModel
import ankit.com.starwarssample.model.PlanetPresentationModel
import ankit.com.starwarssample.model.SpeciesPresentationModel
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

    private val _films = MutableLiveData<List<FilmsPresentationModel>>()
    val films: LiveData<List<FilmsPresentationModel>> = _films

    private val _species = MutableLiveData<List<SpeciesPresentationModel>>()
    val species: LiveData<List<SpeciesPresentationModel>> = _species

    private val _planet = MutableLiveData<PlanetPresentationModel>()
    val planet: LiveData<PlanetPresentationModel> = _planet

    fun getCharacterDetails(planetUrl: String, speciesUrls: List<String>, filmUrls: List<String>) {
        viewModelScope.launch {
            getPlanetDetails(planetUrl)
            getSpeciesDetails(speciesUrls)
            getFilmsDetails(filmUrls)
        }
    }

    private suspend fun getFilmsDetails(urls: List<String>) {
          getFilmsUseCase(urls)
              .map { it.successOr(emptyList()) }
              .collect{
                  _films.value = it.toPresentationFilmsList()
              }
    }

    private suspend fun getSpeciesDetails(urls: List<String>) {
        getSpeciesUseCase(urls)
            .map { it.successOr(null) }
            .collect{
                _species.value = it?.toPresentationSpeciesList()
            }

    }

    private suspend fun getPlanetDetails(url: String) {
        getPlanetUseCase(url)
            .map { it.successOr(null) }
            .collect{
                _planet.value = it?.toPresentation()
            }

    }


}