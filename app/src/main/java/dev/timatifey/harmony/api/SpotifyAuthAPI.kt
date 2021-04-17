package dev.harmony.api

import dev.timatifey.harmony.api.dto.SpotifyTokenResponse
import retrofit2.http.*

interface SpotifyAuthAPI {
    /**
     * Source: [https://developer.spotify.com/documentation/general/guides/authorization-guide
     * /#authorization-code-flow-with-proof-key-for-code-exchange-pkce]
     */

    @FormUrlEncoded
    @POST("/api/token")
    suspend fun exchangesCodeForToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectURI: String,
        @Field("code_verifier") codeVerifier: String,
    ): SpotifyTokenResponse

    @FormUrlEncoded
    @POST("/api/token")
    suspend fun refreshToken(
        @Field("grant_type") grantType: String, // = "refresh_token"
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String,
    ): SpotifyTokenResponse
}