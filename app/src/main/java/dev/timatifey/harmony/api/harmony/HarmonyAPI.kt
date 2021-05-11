package dev.timatifey.harmony.api.harmony

import dev.timatifey.harmony.api.harmony.dto.*
import dev.timatifey.harmony.data.model.harmony.Token
import retrofit2.http.*

interface HarmonyAPI {

    // AUTH
    @FormUrlEncoded
    @POST("/api/signup")
    suspend fun registerUser(
        @Field("login") login: String,
        @Field("password") password: String,
        @Field("email") email: String,
    ): HarmonyAuthResponseDto

    @FormUrlEncoded
    @POST("/api/auth")
    suspend fun authUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): HarmonyAuthResponseDto

    @GET("/api/validate")
    suspend fun isTokenValidate(
        @Header("Authorization") authToken: Token
    ): HarmonyAuthResponseDto

    // USER
    @GET("/api/user")
    suspend fun getUser(@Header("Authorization") token: Token): HarmonyUserResponseDto

    @POST("/api/user/integrate")
    suspend fun integrateSpotify(
        @Header("Authorization") authToken: Token,
        @Body harmonyIntegrateSpotifyBodyDto: HarmonyIntegrateSpotifyBodyDto
    ): HarmonySimpleResponseDto

    @POST("/api/user/disintegrate")
    suspend fun disintegrateSpotify(
        @Header("Authorization") authToken: Token,
    ): HarmonySimpleResponseDto

    @GET("/api/user/groups")
    suspend fun getGroups(@Header("Authorization") token: Token): HarmonyGroupsResponseDto

    // GROUPS
    @FormUrlEncoded
    @POST("/api/group/create")
    suspend fun createGroup(
        @Header("Authorization") authToken: Token,
        @Field("groupName") groupName: String,
        @Field("description") description: String,
        @Field("avatar_url") avatarUrl: String?,
    ): HarmonyGroupResponseDto

    @GET("/api/group/invite")
    suspend fun createInviteCode(
        @Header("Authorization") token: Token,
        @Query("groupId") groupId: Long,
        @Query("days") codeLifetime: Int,
    ): HarmonySimpleResponseDto

    @FormUrlEncoded
    @POST("/api/group/join")
    suspend fun joinGroup(
        @Header("Authorization") authToken: Token,
        @Field("inviteCode") inviteCode: String,
    ): HarmonyGroupResponseDto

    @GET("/api/group/leave")
    suspend fun leaveGroup(
        @Header("Authorization") authToken: Token,
        @Query("groupId") groupId: Long,
    ): HarmonySimpleResponseDto

    // MUSIC
    @FormUrlEncoded
    @POST("/api/group/music/add")
    suspend fun addSong(
        @Header("Authorization") authToken: Token,
        @Field("groupId") groupId: Long,
        @Field("spotifyId") trackId: Long,
    ): HarmonySimpleResponseDto

    @FormUrlEncoded
    @POST("/api/group/music/remove")
    suspend fun removeSong(
        @Header("Authorization") authToken: Token,
        @Field("groupId") groupId: Long,
        @Field("spotifyId") spotifyId: Long,
    ): HarmonySimpleResponseDto

    @GET("/api/group/music/get")
    suspend fun getAllSongs(
        @Header("Authorization") authToken: Token,
        @Query("groupId") groupId: Long,
    ): HarmonySongsResponseDto
}