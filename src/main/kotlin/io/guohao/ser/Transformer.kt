package io.guohao.ser

interface Transformer<T, R> {
    fun transformTo(t: T): R
    fun transformFrom(r: R): T
}