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

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.InputStream
import java.io.OutputStream

class KtJsonSerializer<T>(
    private val serializer: KSerializer<T>,
) : Serializer<T> {

    override fun deserialize(ins: InputStream): T {
        return Json.decodeFromStream(serializer, ins)
    }

    override fun serialize(data: T, outs: OutputStream) {
        Json.encodeToStream(serializer, data, outs)
    }

    override fun deserialize(array: ByteArray): T {
        return Json.decodeFromString(serializer, String(array))
    }

    override fun serialize(data: T): ByteArray {
        return Json.encodeToString(serializer, data).toByteArray()
    }
}
