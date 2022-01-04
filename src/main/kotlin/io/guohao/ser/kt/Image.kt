/*
 * Copyright 2022 guohaoice@gmail.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
