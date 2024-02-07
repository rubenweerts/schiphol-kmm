package nl.schiphol.schipholkmm.data.mapping

import kotlin.jvm.JvmName

abstract class Mapper<ModelA, ModelB> {
    open operator fun invoke(value: ModelA): ModelB = transform(value)

    @JvmName("invokeNullable")
    operator fun invoke(value: ModelA?): ModelB? = value?.let { invoke(it) }

    @JvmName("invokeList")
    operator fun invoke(values: List<ModelA>) = values.map { invoke(it) }

    @JvmName("invokeNullableList")
    operator fun invoke(values: List<ModelA>?) = values?.map { invoke(it) }

    @JvmName("invokeNullableListWithNullableItems")
    operator fun invoke(values: List<ModelA?>?) = values?.map { invoke(it) }

    protected abstract fun transform(value: ModelA): ModelB
}
