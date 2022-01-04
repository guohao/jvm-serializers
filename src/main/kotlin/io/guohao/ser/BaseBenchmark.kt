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
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
abstract class BaseBenchmark<T>(
    dataSource: String,
    private val nameToSerializer: Map<String, Serializer<T>>,
    private val type: Class<T>
) {
    @Param("fastjson", "kotlinx-json")
    var serializerName: String? = "fastjson"

    private final val content = this::class.java.getResource(dataSource)!!.readText(Charset.defaultCharset())
        .let { JSON.parseObject(it, type) }

    private var serializer: Serializer<T>? = null
    private var serialized: ByteArray? = null

    @Setup
    fun setUp() {
        this.serializer = nameToSerializer[serializerName]
        this.serialized = serializer!!.serialize(content)
    }

    @Benchmark
    fun create() {
        serializer!!.create(content)
    }

    @Benchmark
    fun serialize() {
        serializer!!.serialize(content)
    }

    @Benchmark
    fun deserialize() {
        serializer!!.deserialize(serialized!!)
    }
}
