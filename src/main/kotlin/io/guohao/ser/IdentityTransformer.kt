package io.guohao.ser

class IdentityTransformer<T> : Transformer<T, T> {
    override fun transformTo(t: T): T {
        return t
    }

    override fun transformFrom(r: T): T {
        return r
    }
}