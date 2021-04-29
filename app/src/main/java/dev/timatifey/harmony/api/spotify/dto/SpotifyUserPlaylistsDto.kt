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

    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,

    @SerializedName("href")
    val href: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("images")
    val images: List<ImageSpotify>,

    @SerializedName("name")
    val name: String,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("public")
    val public: Boolean,

    @SerializedName("snapshot_id")
    val snapshotId: String,

    @SerializedName("tracks")
    val tracks: List<Tracks>,

    @SerializedName("type")
    val type: String,

    @SerializedName("uri")
    val uri: String,
) {
    data class ExternalUrls(
        @SerializedName("spotify")
        val spotifyUrl: String,
    )

    data class ImageSpotify(
        @SerializedName("height")
        val height: Int?,

        @SerializedName("url")
        val url: String,

        @SerializedName("width")
        val width: Int?
    )

    data class Owner(
        @SerializedName("external_urls")
        val externalUrls: ExternalUrls,

        @SerializedName("href")
        val href: String,

        @SerializedName("id")
        val id: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("uri")
        val uri: String,
    )

    data class Tracks(
        @SerializedName("href")
        val href: String,

        @SerializedName("total")
        val total: Int,
    )
}
