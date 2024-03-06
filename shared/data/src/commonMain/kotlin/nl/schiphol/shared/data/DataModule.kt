package nl.schiphol.shared.data

import org.koin.dsl.module

fun sharedDataModule() = module {
    single<TestRepository> { TestRepositoryImpl() }
}
