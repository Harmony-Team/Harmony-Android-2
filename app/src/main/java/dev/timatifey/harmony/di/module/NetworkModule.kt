package dev.timatifey.harmony.di.module

import dagger.Module
import dagger.Provides
import dev.timatifey.harmony.api.harmony.HarmonyAPI
import dev.timatifey.harmony.api.spotify.SpotifyAPI
import dev.timatifey.harmony.api.spotify.SpotifyAuthAPI
import dev.timatifey.harmony.Config.Companion.BASE_URL
import dev.timatifey.harmony.Config.Companion.SPOTIFY_API_BASE_URL
import dev.timatifey.harmony.Config.Companion.SPOTIFY_AUTH_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideHarmonyApiService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): HarmonyAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(HarmonyAPI::class.java)

    @Singleton
    @Provides
    fun provideSpotifyAuthService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): SpotifyAuthAPI =
        Retrofit.Builder()
            .baseUrl(SPOTIFY_AUTH_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(SpotifyAuthAPI::class.java)

    @Singleton
    @Provides
    fun provideSpotifyApiService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): SpotifyAPI =
        Retrofit.Builder()
            .baseUrl(SPOTIFY_API_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(SpotifyAPI::class.java)
}