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

import com.alibaba.fastjson.JSON
import kotlinx.benchmark.Mode
import org.openjdk.jmh.annotations.*
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
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
