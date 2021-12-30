package io.guohao.ser.kt

import kotlinx.serialization.Serializable

@Serializable
 data class Image(
    var uri: String? = null,
    var title: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var size: Size? = null
)

enum class Size {
    SMALL, LARGE
}

 fun fromOriginImage(image: io.guohao.ser.Image?): Image? =
    image?.let {
        Image(
            image.uri,
            image.title,
            image.width,
            image.height,
            fromOriginSize(image.size)
        )
    }

 fun toOriginImage(image: Image?): io.guohao.ser.Image? = image?.let {
    io.guohao.ser.Image(
        image.uri,
        image.title,
        image.width,
        image.height,
        toOriginSize(image.size)
    )
}

 fun fromOriginSize(player: io.guohao.ser.Image.Size?): Size? =
    when (player) {
        io.guohao.ser.Image.Size.SMALL -> Size.SMALL
        io.guohao.ser.Image.Size.LARGE -> Size.LARGE
        else -> null
    }

 fun toOriginSize(player: Size?): io.guohao.ser.Image.Size? =
    when (player) {
        Size.SMALL -> io.guohao.ser.Image.Size.SMALL
        Size.LARGE -> io.guohao.ser.Image.Size.LARGE
        else -> null
    }