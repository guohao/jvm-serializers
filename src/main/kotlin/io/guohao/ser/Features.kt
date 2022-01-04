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

data class Features(
    val format: Format,
    val graphSupport: GraphSupport,
    val schema: SCHEMA
) {
    constructor(format: Format) : this(format, GraphSupport.UNKNOWN, SCHEMA.MANUAL_OPT)
    constructor(format: Format, graphSupport: GraphSupport) : this(format, graphSupport, SCHEMA.MANUAL_OPT)
    constructor(graphSupport: GraphSupport) : this(Format.MISC, graphSupport, SCHEMA.MANUAL_OPT)
    constructor(schema: SCHEMA) : this(Format.MISC, GraphSupport.UNKNOWN, schema)

    enum class Format {
        BIN,
        BIN_POLYGLOT,
        JSON,
        XML,
        YAML,
        MISC
    }

    enum class GraphSupport {
        FLAT,
        FULL_GRAPH,
        UNKNOWN
    }

    enum class SCHEMA {
        ZERO_KNOWLEDGE,
        CLASSES_KNOWN,
        MANUAL_OPT,
    }
}
