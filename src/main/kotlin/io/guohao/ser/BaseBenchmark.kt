package io.guohao.ser

import com.alibaba.fastjson.JSON
import org.openjdk.jmh.annotations.*
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 3)
@Measurement(iterations = 5, time = 20, timeUnit = TimeUnit.SECONDS)
abstract class BaseBenchmark<OriginType, TransType>(
    private val serializer: Serializer<TransType>,
    private val dataSource: String,
    private val transformer: Transformer<OriginType, TransType>,
    private val type: Class<OriginType>
) {
    private final val content = this::class.java.getResource(dataSource)!!.readText(Charset.defaultCharset())
        .let { JSON.parseObject(it, type) }

    private final val serialized: ByteArray = serializer.serialize(transformer.transformTo(content))

    @Setup
    fun setUp() {
    }

//    @Benchmark
//    fun create() {
//    }

    @Benchmark
    fun serialize() {
        serializer.serialize(transformer.transformTo(content))
    }

    @Benchmark
    fun deserialize() {
        transformer.transformFrom(serializer.deserialize(serialized))
    }
}
