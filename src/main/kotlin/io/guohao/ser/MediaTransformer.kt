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

package io.guohao.ser

object MediaTransformer : Transformer<MediaContent, MediaContent> {
    override fun transformTo(t: MediaContent): MediaContent = copy(t)

    private fun copy(t: MediaContent): MediaContent =
        t.images.map { i -> Image(i.uri, i.title, i.width, i.height, i.size) }
            .let { MediaContent(copy(t.media), it) }

    private fun copy(m: Media): Media =
        Media(
            m.uri,
            m.title,
            m.width,
            m.height,
            m.format,
            m.duration,
            m.size,
            m.bitrate,
            m.hasBitrate,
            ArrayList(m.persons),
            m.player,
            m.copyright
        )


    override fun transformFrom(r: MediaContent): MediaContent = copy(r)
}