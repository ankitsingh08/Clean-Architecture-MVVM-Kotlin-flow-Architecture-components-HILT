package ankit.com.starwarssample.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.model.CharacterDomainModel
import ankit.com.domain.model.FilmsDomainModel
import ankit.com.domain.usecase.SearchCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by AnkitSingh on 12/12/20.
 */
private const val SEARCH_DELAY_MILLIS = 500L
private const val MIN_QUERY_LENGTH = 3

class CharacterSearchViewModel @ViewModelInject constructor(
        private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

//    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
//
//    private val _characters = queryChannel
//        .asFlow()
//        .debounce(SEARCH_DELAY_MILLIS)
//        .mapLatest { offeredString ->
//            if (offeredString.length >= MIN_QUERY_LENGTH) {
//                getStarWarsCharacters(offeredString)
//                        .map {
//                            it.successOr(emptyList())
//                        }
//            } else {
//                emptyList()
//            }
//        }
//        .catch {
//            // Log Error
//        }
//
//    val characters =_characters.asLiveData()

    private val _characters = MutableLiveData<List<CharacterDomainModel>>()
    val characters: LiveData<List<CharacterDomainModel>> = _characters

    fun searchStarWarCharacters(characterName: String) {
        viewModelScope.launch {
            getStarWarsCharacters(characterName)
        }
    }

    private suspend fun getStarWarsCharacters(characterName: String) {
        searchCharactersUseCase(characterName)
                .debounce(SEARCH_DELAY_MILLIS)
                .mapLatest {
                    if (characterName.length >= MIN_QUERY_LENGTH) {
                        getStarWarsCharacters(characterName)
                        it.successOr(emptyList())
                    } else {
                        emptyList()
                    }
                }
                .collect {
                    _characters.value = it
                }
    }
}