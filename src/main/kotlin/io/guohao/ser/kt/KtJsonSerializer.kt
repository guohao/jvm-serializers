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