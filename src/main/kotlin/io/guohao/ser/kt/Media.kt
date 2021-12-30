package io.guohao.ser.kt

import kotlinx.serialization.Serializable

@Serializable
 data class Media(
    var uri: String? = null,
    var title: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var format: String? = null,
    var duration: Long = 0,
    var size: Long = 0,
    var bitrate: Int = 0,
    var hasBitrate: Boolean = false,
    var persons: List<String>? = null,
    var player: Player? = null,
    var copyright: String? = null,
)

enum class Player {
    JAVA, FLASH;
}

 fun fromOriginPlayer(player: io.guohao.ser.Media.Player?): Player? =
    when (player) {
        io.guohao.ser.Media.Player.JAVA -> Player.JAVA
        io.guohao.ser.Media.Player.FLASH -> Player.FLASH
        else -> null
    }

 fun toOriginPlayer(player: Player?): io.guohao.ser.Media.Player? =
    when (player) {
        Player.JAVA -> io.guohao.ser.Media.Player.JAVA
        Player.FLASH -> io.guohao.ser.Media.Player.FLASH
        else -> null
    }

 fun fromOriginMedia(media: io.guohao.ser.Media?): Media? = media?.let {
    Media(
        media.uri,
        media.title,
        media.width,
        media.height,
        media.format,
        media.duration,
        media.size,
        media.bitrate,
        media.hasBitrate,
        media.persons,
        fromOriginPlayer(media.player),
        media.copyright,
    )
}

 fun toOriginMedia(media: Media?): io.guohao.ser.Media? = media?.let {
    io.guohao.ser.Media(
        media.uri,
        media.title,
        media.width,
        media.height,
        media.format,
        media.duration,
        media.size,
        media.bitrate,
        media.hasBitrate,
        media.persons,
        toOriginPlayer(media.player),
        media.copyright,
    )
}


