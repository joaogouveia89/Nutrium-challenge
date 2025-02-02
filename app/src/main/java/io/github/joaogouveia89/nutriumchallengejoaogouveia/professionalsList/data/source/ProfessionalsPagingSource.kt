package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import okio.IOException
import retrofit2.HttpException

class ProfessionalsPagingSource(
    private val remoteDataSource: ProfessionalsRemoteSource,
    private val filterType: String
) : PagingSource<Int, Professional>() {
    override fun getRefreshKey(state: PagingState<Int, Professional>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Professional> {
        return try {
            val pageNumber = params.key ?: 1
            val professionals = remoteDataSource.getProfessionals(
                filterType = filterType,
                limit = LIMIT,
                offset = pageNumber + LIMIT
            )

            LoadResult.Page(
                data = professionals,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (professionals.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 4
    }
}