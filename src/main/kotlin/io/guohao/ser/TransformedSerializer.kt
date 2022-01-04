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

import java.io.InputStream
import java.io.OutputStream

class TransformedSerializer<T, R>(
    private val delegate: Serializer<R>,
    private val transformer: Transformer<T, R>
) : Serializer<T> {
    override fun deserialize(ins: InputStream): T = transformer.transformFrom(delegate.deserialize(ins))

    override fun deserialize(array: ByteArray): T = transformer.transformFrom(delegate.deserialize(array))

    override fun serialize(data: T, outs: OutputStream) = delegate.serialize(transformer.transformTo(data), outs)

    override fun serialize(data: T): ByteArray = delegate.serialize(transformer.transformTo(data))

    override fun create(data: T) {
        transformer.transformTo(data)
    }
}

infix fun <T, R> Serializer<R>.wrap(transformer: Transformer<T, R>) = TransformedSerializer(this, transformer)
