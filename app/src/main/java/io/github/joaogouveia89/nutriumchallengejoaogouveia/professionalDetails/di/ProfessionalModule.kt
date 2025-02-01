package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.repository.ProfessionalRepositoryImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.source.ProfessionalRemoteSourceImpl
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.ProfessionalRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalRemoteSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfessionalModule {

    @Provides
    @Singleton
    fun provideProfessionalRemoteSource(
        service: NutriumService
    ): ProfessionalRemoteSource = ProfessionalRemoteSourceImpl(service)

    @Provides
    @Singleton
    fun provideProfessionalRepository(
        source: ProfessionalRemoteSource
    ): ProfessionalRepository = ProfessionalRepositoryImpl(source)

//    @Provides
//    @Singleton
//    fun provideRandomUserRepository(
//        remoteSource: ProfessionalsRemoteSource,
//        @IoDispatcher dispatcher: CoroutineDispatcher
//    ): ProfessionalsRepository = ProfessionalsRepositoryImpl(
//        professionalsRemoteSource = remoteSource,
//        dispatcher = dispatcher
//    )
}