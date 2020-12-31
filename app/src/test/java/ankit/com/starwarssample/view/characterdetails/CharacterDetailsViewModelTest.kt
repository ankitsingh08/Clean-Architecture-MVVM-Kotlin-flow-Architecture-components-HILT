package ankit.com.starwarssample.view.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.usecase.GetFilmsUseCase
import ankit.com.domain.usecase.GetPlanetUseCase
import ankit.com.domain.usecase.GetSpeciesUseCase
import ankit.com.starwarssample.mapper.toPresentation
import ankit.com.starwarssample.mapper.toPresentationFilmsList
import ankit.com.starwarssample.mapper.toPresentationSpeciesList
import ankit.com.starwarssample.model.FilmsPresentationModel
import ankit.com.starwarssample.model.PlanetPresentationModel
import ankit.com.starwarssample.model.SpeciesPresentationModel
import ankit.com.starwarssample.testdata.PresentationTestData.getFilmsList
import ankit.com.starwarssample.testdata.PresentationTestData.getFilmsUrl
import ankit.com.starwarssample.testdata.PresentationTestData.getPlanetDetails
import ankit.com.starwarssample.testdata.PresentationTestData.getPlanetUrl
import ankit.com.starwarssample.testdata.PresentationTestData.getSpeciesList
import ankit.com.starwarssample.testdata.PresentationTestData.getSpeciesUrl
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by AnkitSingh on 12/31/20.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsViewModelTest{
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getFilmsUseCase: GetFilmsUseCase

    @Mock
    private lateinit var getPlanetUseCase: GetPlanetUseCase

    @Mock
    private lateinit var getSpeciesUseCase: GetSpeciesUseCase

    @Mock
    private lateinit var filmsListObserver: Observer<List<FilmsPresentationModel>>

    @Mock
    private lateinit var speciesListObserver: Observer<List<SpeciesPresentationModel>>

    @Mock
    private lateinit var planetObserver: Observer<PlanetPresentationModel>

    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterDetailsViewModel(getFilmsUseCase, getPlanetUseCase, getSpeciesUseCase)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get films usecase returns list of films success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = getFilmsList()

        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Success(testData)))
        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Success(getPlanetDetails())))
        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Success(getSpeciesList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.films.observeForever(filmsListObserver)

        verify(filmsListObserver).onChanged(testData.toPresentationFilmsList())

        viewModel.films.removeObserver(filmsListObserver)
    }

    @Test
    fun `get films usecase returns empty list in case of error scenario`() = testCoroutineScope.runBlockingTest {
        val exception = Exception()
        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Error(exception)))
        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Success(getPlanetDetails())))
        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Success(getSpeciesList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.films.observeForever(filmsListObserver)

        verify(filmsListObserver).onChanged(emptyList())

        viewModel.films.removeObserver(filmsListObserver)
    }

    @Test
    fun `get planet usecase returns planet details success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = getPlanetDetails()

        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Success(testData)))
        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Success(getFilmsList())))
        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Success(getSpeciesList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.planet.observeForever(planetObserver)

        verify(planetObserver).onChanged(testData.toPresentation())

        viewModel.planet.removeObserver(planetObserver)
    }

    @Test
    fun `get planet usecase returns null value in case of error scenario`() = testCoroutineScope.runBlockingTest {
        val exception = Exception()

        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Error(exception)))
        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Success(getFilmsList())))
        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Success(getSpeciesList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.planet.observeForever(planetObserver)

        verify(planetObserver).onChanged(null)

        viewModel.films.removeObserver(filmsListObserver)
    }

    @Test
    fun `get species usecase returns species list success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = getSpeciesList()

        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Success(testData)))
        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Success(
            getPlanetDetails())))
        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Success(getFilmsList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.species.observeForever(speciesListObserver)

        verify(speciesListObserver).onChanged(testData.toPresentationSpeciesList())

        viewModel.species.removeObserver(speciesListObserver)
    }

    @Test
    fun `get species usecase returns empty list in case of error scenario`() = testCoroutineScope.runBlockingTest {
        val exception = Exception()

        whenever(getSpeciesUseCase(getSpeciesUrl())).thenReturn(flowOf(ApiResponse.Error(exception)))
        whenever(getPlanetUseCase(getPlanetUrl())).thenReturn(flowOf(ApiResponse.Success(
            getPlanetDetails())))
        whenever(getFilmsUseCase(getFilmsUrl())).thenReturn(flowOf(ApiResponse.Success(getFilmsList())))

        viewModel.getCharacterDetails(getPlanetUrl(), getSpeciesUrl(), getFilmsUrl())
        viewModel.species.observeForever(speciesListObserver)

        verify(speciesListObserver).onChanged(emptyList())

        viewModel.species.removeObserver(speciesListObserver)
    }
}