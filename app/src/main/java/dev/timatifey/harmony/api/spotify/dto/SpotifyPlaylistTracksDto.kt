package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyPlaylistTracksDto(
    @SerializedName("href")
    val href: String,

    @SerializedName("items")
    val items: List<PlaylistTrackObject>?,
)

data class PlaylistTrackObject(
    @SerializedName("added_at")
    val addedAt: String,

    @SerializedName("track")
    val track: TrackObject
)

data class TrackObject(
    @SerializedName("album")
    val album: Album,

    @SerializedName("duration_ms")
    val durationMs: Long,

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("preview_url")
    val previewUrl: String,

    @SerializedName("href")
    val href: String,

    @SerializedName("uri")
    val uri: String,
)

data class Album(
    @SerializedName("artists")
    val artists: List<Artist>,

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("images")
    val images: List<ImageSpotify>
)

data class Artist(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,
)