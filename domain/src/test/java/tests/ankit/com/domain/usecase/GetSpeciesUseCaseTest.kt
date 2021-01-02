package tests.ankit.com.domain.usecase

import ankit.com.domain.core.ApiResponse
import ankit.com.domain.core.successOr
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import ankit.com.domain.usecase.GetSpeciesUseCase
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
import testdata.DomainTestData.getSpeciesList
import testdata.DomainTestData.getSpeciesUrl
import java.io.IOException

/**
 * Created by AnkitSingh on 1/1/21.
 */
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetSpeciesUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var starWarCharacterDetailRepository: StarWarCharacterDetailRepository

    private lateinit var testCase: GetSpeciesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        testCase = GetSpeciesUseCase(starWarCharacterDetailRepository, testCoroutineDispatcher)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `species list is returned by repository success scenario`() = testCoroutineScope.runBlockingTest {
        val expectedResult =  flowOf(ApiResponse.Success(getSpeciesList()))
        whenever(starWarCharacterDetailRepository.getCharacterSpeciesDetails(getSpeciesUrl())).thenReturn(expectedResult)

        val result = testCase.invoke(getSpeciesUrl())

        result.collect{ data ->
            assertEquals(getSpeciesList(), data.successOr(emptyList()))
        }
    }

    @Test
    fun  `repository returns error when exception is thrown while fetching species list`()  = testCoroutineScope.runBlockingTest{
        val expectedError =  ApiResponse.Error(IOException())
        whenever(starWarCharacterDetailRepository.getCharacterSpeciesDetails(getSpeciesUrl())).thenReturn(
            flowOf(expectedError))

        val result = testCase.invoke(getSpeciesUrl())

        result.collect{ error ->
            assertEquals(expectedError, error)
        }
    }
}