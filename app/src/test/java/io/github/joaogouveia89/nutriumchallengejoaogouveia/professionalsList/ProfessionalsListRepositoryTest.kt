package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.github.joaogouveia89.nutriumchallengejoaogouveia.MainCoroutineRule
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.repository.ProfessionalsRepositoryImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.source.ProfessionalsPagingSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.ProfessionalListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfessionalsListRepositoryTest {
    private val testScheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(testScheduler)

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(dispatcher)
    private val mockSource = mockk<ProfessionalsRemoteSource>(relaxed = true)

    private val professionalsMock = listOf(
        mockk<Professional>(),
        mockk<Professional>(),
        mockk<Professional>(),
        mockk<Professional>(),
        mockk<Professional>(),
        mockk<Professional>(),
    )

    private var testsCache = mutableMapOf<String, MutableMap<Int, List<Professional>>>()

    private lateinit var repository: ProfessionalsRepository

    @Before
    fun setup() {
        testsCache = mutableMapOf()
        repository = ProfessionalsRepositoryImpl(
            professionalsRemoteSource = mockSource,
            dispatcher = dispatcher,
            cachedProfessionals = testsCache
        )
    }

    @Test
    fun `getProfessionals returns data from remote source`() = runTest {
        // Given
        val filterType = "testFilter"
        val pagingConfig = PagingConfig(pageSize = 2, initialLoadSize = 2)

        coEvery { mockSource.getProfessionals(any(), any(), any()) } returns professionalsMock
        coEvery { mockSource.getProfessionalsPagingSource(any(), any()) } returns ProfessionalsPagingSource(
            remoteDataSource = mockSource,
            filterType = filterType,
            cache = testsCache
        )

        // When
        val result = repository.getProfessionals(filterType, pagingConfig).asSnapshot()

        // Then
        assertEquals(professionalsMock, result)
    }

}