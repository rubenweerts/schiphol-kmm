package nl.schiphol.shared.data

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

interface TestRepository {
    fun normalFun(): String
    suspend fun suspendFun(): String
    fun flowFun(): Flow<String>
}

internal class TestRepositoryImpl : TestRepository {
    override fun normalFun(): String {
        return "normalFun"
    }

    override suspend fun suspendFun(): String {
        delay(4000)
        return "suspendFun"
    }

    override fun flowFun(): Flow<String> = flow {
        var tick = 0
        while (currentCoroutineContext().isActive) {
            tick++
            emit("flowFun tick = $tick")
            delay(1000)
        }
    }
}
