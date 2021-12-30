package io.guohao.ser.kt

import kotlinx.serialization.Serializable

@Serializable
data class MediaContent(
    var media: Media? = null,
    var images: List<Image?>? = null
)

fun fromOriginMediaContent(mediaContent: io.guohao.ser.MediaContent): MediaContent = MediaContent(
    fromOriginMedia(mediaContent.getMedia()),
    mediaContent.images?.map { fromOriginImage(it) }
)

fun toOriginMediaContent(mediaContent: MediaContent): io.guohao.ser.MediaContent =
    io.guohao.ser.MediaContent(toOriginMedia(mediaContent.media), mediaContent.images?.map { toOriginImage(it) })
