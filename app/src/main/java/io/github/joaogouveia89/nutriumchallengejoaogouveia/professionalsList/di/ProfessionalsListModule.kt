package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.di.IoDispatcher
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.repository.ProfessionalsRepositoryImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.source.ProfessionalsRemoteSourceImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfessionalsListModule {

    @Provides
    @Singleton
    fun provideProfessionalsRemoteSource(
        service: NutriumService
    ): ProfessionalsRemoteSource = ProfessionalsRemoteSourceImpl(service)

    @Provides
    @Singleton
    fun provideRandomUserRepository(
        remoteSource: ProfessionalsRemoteSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): ProfessionalsRepository = ProfessionalsRepositoryImpl(
        professionalsRemoteSource = remoteSource,
        dispatcher = dispatcher
    )
}