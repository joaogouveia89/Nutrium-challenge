package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.nutriumchallengejoaogouveia.BuildConfig
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.LogJsonInterceptor
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.ParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_SECONDS = 15L
    private const val BASE_URL = "http://10.0.2.2:5000"

    @Provides
    fun provideParamsInterceptor(): ParamsInterceptor = ParamsInterceptor()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    fun provideLogJsonInterceptor(): LogJsonInterceptor =
        LogJsonInterceptor()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory
            .create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )


    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        logJsonInterceptor: LogJsonInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)
            .addInterceptor(logJsonInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideNutriumService(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NutriumService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NutriumService::class.java)
}