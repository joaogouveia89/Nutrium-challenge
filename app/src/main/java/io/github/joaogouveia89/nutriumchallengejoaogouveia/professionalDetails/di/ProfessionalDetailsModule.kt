package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.repository.ProfessionalDetailsRepositoryImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.source.ProfessionalDetailsRemoteSourceImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.ProfessionalDetailsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalDetailsRemoteSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfessionalDetailsModule {

    @Provides
    @Singleton
    fun provideProfessionalDetailsRemoteSource(
        service: NutriumService
    ): ProfessionalDetailsRemoteSource = ProfessionalDetailsRemoteSourceImpl(service)

    @Provides
    @Singleton
    fun provideProfessionalDetailsRepository(
        source: ProfessionalDetailsRemoteSource
    ): ProfessionalDetailsRepository = ProfessionalDetailsRepositoryImpl(source)
}