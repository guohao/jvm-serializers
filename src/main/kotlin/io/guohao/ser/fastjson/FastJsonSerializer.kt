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