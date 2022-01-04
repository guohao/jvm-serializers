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

package io.guohao.ser.testcase

import io.guohao.ser.*
import io.guohao.ser.fastjson.FastJsonSerializer
import io.guohao.ser.kt.KtMediaContentTransformer

private val serializers: Map<String, Serializer<MediaContent>> = mapOf(
    "fastjson" to (FastJsonSerializer(MediaContent::class.java) wrap MediaTransformer),
    "kotlinx-json" to (KtJsonSerializer(io.guohao.ser.kt.MediaContent.serializer()) wrap KtMediaContentTransformer)
)

class MediaContentBenchmark :
    BaseBenchmark<MediaContent>(
        "/data/media.1.json",
        serializers,
        MediaContent::class.java
    )
