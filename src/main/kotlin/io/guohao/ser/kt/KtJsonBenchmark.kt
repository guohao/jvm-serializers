package io.guohao.ser.kt

import io.guohao.ser.KtJsonSerializer
import io.guohao.ser.MediaContent
import io.guohao.ser.Transformer
import io.guohao.ser.testcase.MediaContentBenchmark

class KTJsonBenchmark : MediaContentBenchmark<io.guohao.ser.kt.MediaContent>(
    KtJsonSerializer(io.guohao.ser.kt.MediaContent.serializer()), KtMediaContentTransformer
)

object KtMediaContentTransformer : Transformer<MediaContent, io.guohao.ser.kt.MediaContent> {
    override fun transformTo(t: MediaContent): io.guohao.ser.kt.MediaContent = fromOriginMediaContent(t)

    override fun transformFrom(r: io.guohao.ser.kt.MediaContent): MediaContent = toOriginMediaContent(r)
}