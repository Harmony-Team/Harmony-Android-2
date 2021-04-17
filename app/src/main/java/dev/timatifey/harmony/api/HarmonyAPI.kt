package dev.harmony.api

import dev.timatifey.harmony.api.dto.HarmonyResponse
import dev.timatifey.harmony.api.dto.IntegrateSpotifyBody
import retrofit2.http.*

interface HarmonyAPI {

    @FormUrlEncoded
    @POST("/api/signup")
    suspend fun registerUser(
            @Field("login") login: String,
            @Field("password") password: String,
            @Field("email") email: String,
    ): HarmonyResponse

    @GET("/api/user")
    suspend fun getUser(@Header("Authorization") token: String): HarmonyResponse

    @FormUrlEncoded
    @POST("/api/auth")
    suspend fun auth(
        @Field("login") login: String,
        @Field("password") password: String,
    ): HarmonyResponse

    @GET("/api/validate")
    suspend fun isTokenValidate(
        @Header("Authorization") authToken: String,
        @Query("token") token: String
    ): HarmonyResponse

    @POST("/api/user/integrate")
    suspend fun integrateSpotify(
        @Header("Authorization") authToken: String,
        @Body integrateSpotifyBody: IntegrateSpotifyBody
    ): HarmonyResponse

    @FormUrlEncoded
    @POST("/api/user/disintegrate")
    suspend fun disintegrateSpotify(
        @Header("Authorization") authToken: String,
    ): HarmonyResponse
}