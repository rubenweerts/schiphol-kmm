package org.schiphol.data.remote.support

fun (() -> Exception?).checkShouldThrow() {
    invoke()?.let { throw(it) }
}

inline fun <T> (() -> Exception?).onFailed(block: (Result<T>) -> Unit) {
    invoke()?.let { block(Result.failure(it)) }
}