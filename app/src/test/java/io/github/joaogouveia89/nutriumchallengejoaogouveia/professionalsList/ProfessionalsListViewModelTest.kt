package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import io.github.joaogouveia89.nutriumchallengejoaogouveia.MainCoroutineRule
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.FilterType
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.ProfessionalListCommand
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.ProfessionalListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfessionalsListViewModelTest {
    private val testScheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(testScheduler)

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(dispatcher)

    private val mockRepository = mockk<ProfessionalsRepository>(relaxed = true)

    private val professionalsMock = mockk<List<Professional>>(relaxed = true)

    private lateinit var viewModel: ProfessionalListViewModel

    @Before
    fun setup() {
        viewModel = ProfessionalListViewModel(mockRepository)
    }

    @Test
    fun `getProfessionals updates uiState with professionals`() = runTest {
        // Given
        val fakePagingData = flowOf(PagingData.from(professionalsMock))
        coEvery { mockRepository.getProfessionals(any(), any()) } returns fakePagingData

        // When
        viewModel.execute(ProfessionalListCommand.GetProfessionals)

        // Then
        backgroundScope.launch(dispatcher) {
            viewModel.uiState.collect{
                val expected = it.professionals.asSnapshot()
                assertEquals(professionalsMock, expected)
            }
        }
    }

    @Test
    fun `changeFilterType updates uiState and fetches professionals`() = runTest {
        // Given
        val filterTypeId = 1 // Assuming there's at least one filter type
        val expectedFilterType = FilterType.getFilters().first { it.ordinal == filterTypeId }
        every { mockRepository.getProfessionals(any(), any()) } returns MutableStateFlow(PagingData.empty())

        // When
        viewModel.execute(ProfessionalListCommand.ChangeFilterType(filterTypeId))

        // Then
        val state = viewModel.uiState.first()
        assertEquals(expectedFilterType, state.filterType)
        verify { mockRepository.getProfessionals(expectedFilterType.apiIdentifier, any()) }
    }

}