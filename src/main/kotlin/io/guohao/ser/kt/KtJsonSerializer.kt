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

class KtJsonSerializer<T>(
    private val serializer: KSerializer<T>,
) : Serializer<T> {

    override fun deserialize(array: ByteArray): T {
        return Json.decodeFromString(serializer, String(array))
    }

    override fun serialize(data: T): ByteArray = Json.encodeToString(serializer, data).toByteArray()

    override fun features() = Features(Features.Format.JSON, Features.GraphSupport.FLAT, Features.SCHEMA.CLASSES_KNOWN)
}
