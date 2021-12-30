package io.guohao.ser.fastjson

import io.guohao.ser.IdentityTransformer
import io.guohao.ser.MediaContent
import io.guohao.ser.testcase.MediaContentBenchmark

class FastjsonBenchmark : MediaContentBenchmark<MediaContent>(FastJsonSerializer(MediaContent::class.java),IdentityTransformer())