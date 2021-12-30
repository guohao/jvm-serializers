package io.guohao.ser

import java.io.InputStream
import java.io.OutputStream

interface Serializer<T> {

    fun deserialize(ins: InputStream): T

    fun deserialize(array: ByteArray): T

    fun serialize(data: T, outs: OutputStream)

    fun serialize(data: T): ByteArray
}