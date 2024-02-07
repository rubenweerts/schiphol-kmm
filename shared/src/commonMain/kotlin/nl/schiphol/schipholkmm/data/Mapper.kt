package nl.schiphol.schipholkmm.data

import nl.schiphol.schipholkmm.data.mapping.Mapper

abstract class ToModuleMapper<Original, Module> : Mapper<Original, Module>()
abstract class ToOriginalMapper<Module, Original> : Mapper<Module, Original>()

interface TwoWayMapper<Original, Module> {
    val toModuleMapper: ToModuleMapper<Original, Module>
    val toOriginalMapper: ToOriginalMapper<Module, Original>
}
