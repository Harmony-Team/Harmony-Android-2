package dev.timatifey.harmony.api.spotify.dto

import com.google.gson.annotations.SerializedName

data class SpotifyUserPlaylistsDto(
    @SerializedName("href")
    val href: String,

    @SerializedName("items")
    val items: List<ItemPlaylistsDto>?,

    @SerializedName("limit")
    val limit: Int,
)

data class ItemPlaylistsDto(
    @SerializedName("collaborative")
    val collaborative: Boolean,

    @SerializedName("href")
    val href: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("images")
    val images: List<ImageSpotify>,

    @SerializedName("name")
    val name: String,

    @SerializedName("public")
    val public: Boolean,

    @SerializedName("tracks")
    val tracks: PlaylistTracksRefObject,

    @SerializedName("type")
    val type: String,

    @SerializedName("uri")
    val uri: String,
)

data class PlaylistTracksRefObject(
    @SerializedName("href")
    val linkPlaylistTracks: String,

    @SerializedName("total")
    val total: Int,
)

data class ImageSpotify(
    @SerializedName("height")
    val height: Int?,

    @SerializedName("url")
    val url: String,

    @SerializedName("width")
    val width: Int?
)