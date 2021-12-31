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

import io.guohao.ser.KtJsonSerializer
import io.guohao.ser.MediaContent
import io.guohao.ser.Transformer
import io.guohao.ser.testcase.MediaContentBenchmark

class KTJsonBenchmark : MediaContentBenchmark<io.guohao.ser.kt.MediaContent>(
    KtJsonSerializer(io.guohao.ser.kt.MediaContent.serializer()), KtMediaContentTransformer
)

object KtMediaContentTransformer : Transformer<MediaContent, io.guohao.ser.kt.MediaContent> {
    override fun transformTo(t: MediaContent): io.guohao.ser.kt.MediaContent = fromOriginMediaContent(t)

    override fun transformFrom(r: io.guohao.ser.kt.MediaContent): MediaContent = toOriginMediaContent(r)
}