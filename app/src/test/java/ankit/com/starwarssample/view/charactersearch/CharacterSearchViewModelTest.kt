package ankit.com.starwarssample.view.charactersearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.domain.core.ApiResponse
import ankit.com.domain.usecase.SearchCharactersUseCase
import ankit.com.starwarssample.mapper.toPresentationCharacterList
import ankit.com.starwarssample.model.CharacterPresentationModel
import ankit.com.starwarssample.testdata.PresentationTestData
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
class CharacterSearchViewModelTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchCharactersUseCase: SearchCharactersUseCase

    @Mock
    private lateinit var charactersListObserver: Observer<List<CharacterPresentationModel>>

    private lateinit var viewModel: CharacterSearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CharacterSearchViewModel(searchCharactersUseCase)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `get characters usecase returns list of star wars characters success scenario`() = testCoroutineScope.runBlockingTest {
        val testData = PresentationTestData.getStarWarCharactersList()

        whenever(searchCharactersUseCase("sky")).thenReturn(flowOf(ApiResponse.Success(testData)))

        viewModel.searchStarWarCharacters("sky")
        viewModel.characters.observeForever(charactersListObserver)

        verify(charactersListObserver).onChanged(testData.toPresentationCharacterList())

        viewModel.characters.removeObserver(charactersListObserver)
    }

    @Test
    fun `get characters usecase returns empty list in case of eroor scenario`() = testCoroutineScope.runBlockingTest {
        val exception = Exception()

        whenever(searchCharactersUseCase("sky")).thenReturn(flowOf(ApiResponse.Error(exception)))

        viewModel.searchStarWarCharacters("sky")
        viewModel.characters.observeForever(charactersListObserver)

        verify(charactersListObserver).onChanged(emptyList())

        viewModel.characters.removeObserver(charactersListObserver)
    }
}