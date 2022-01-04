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

package io.guohao.ser.fastjson

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.serializer.SerializerFeature
import io.guohao.ser.Serializer
import java.io.InputStream
import java.io.OutputStream

class FastJsonSerializer<T>(private val type: Class<T>) : Serializer<T> {

    override fun deserialize(ins: InputStream): T = JSON.parseObject(ins, type, Feature.DisableCircularReferenceDetect)

    override fun deserialize(array: ByteArray): T =
        JSON.parseObject(array, type, Feature.DisableCircularReferenceDetect)

    override fun serialize(data: T, outs: OutputStream) {
        JSON.writeJSONString(
            outs, data, SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.DisableCircularReferenceDetect
        )
    }

    override fun serialize(data: T): ByteArray = JSON.toJSONBytes(
        data,
        SerializerFeature.WriteEnumUsingToString,
        SerializerFeature.DisableCircularReferenceDetect
    )
}
