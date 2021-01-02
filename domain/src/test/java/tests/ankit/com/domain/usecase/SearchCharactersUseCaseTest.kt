package tests.ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.StarWarsRepository
import ankit.com.domain.usecase.SearchCharactersUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import testdata.DomainTestData.getStarWarCharactersList
import java.io.IOException

/**
 * Created by AnkitSingh on 1/1/21.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SearchCharactersUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var starWarsRepository: StarWarsRepository

    private lateinit var testCase: SearchCharactersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        testCase = SearchCharactersUseCase(starWarsRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `character list is returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(getStarWarCharactersList()))
        whenever(starWarsRepository.getStarWarCharacter("sky")).thenReturn(expectedResult)

        val result = testCase.invoke("sky")

        result.collect{ data ->
            assertEquals(getStarWarCharactersList(), data.successOr(emptyList()))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching character details`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(starWarsRepository.getStarWarCharacter("Luke")).thenReturn(flowOf(expectedError))

        val result = testCase.invoke("Luke")

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}