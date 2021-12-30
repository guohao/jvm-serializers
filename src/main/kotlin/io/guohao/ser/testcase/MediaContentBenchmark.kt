package io.guohao.ser.testcase

import io.guohao.ser.BaseBenchmark
import io.guohao.ser.MediaContent
import io.guohao.ser.Serializer
import io.guohao.ser.Transformer

abstract class MediaContentBenchmark<T>(
    serializer: Serializer<T>,
    transformer: Transformer<MediaContent, T>
) :
    BaseBenchmark<MediaContent, T>(
        serializer, "/data/media.1.json",
        transformer, MediaContent::class.java
    )