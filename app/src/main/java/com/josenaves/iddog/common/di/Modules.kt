package com.josenaves.iddog.common.di

import com.josenaves.iddog.data.AuthRepository
import com.josenaves.iddog.data.remote.AuthRemoteDatasource
import com.josenaves.iddog.data.ConfigurationRepository
import com.josenaves.iddog.data.local.ConfigurationLocalDatasource
import com.josenaves.iddog.data.remote.api.DogApiClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val applicationModule = module(override = true) {

    // remote api
    single { DogApiClient.create() }

    // datasources
    single { AuthRemoteDatasource(get()) }
    single { ConfigurationLocalDatasource(androidContext()) }

    // repositories
    single { ConfigurationRepository(get()) }
    single { AuthRepository(get()) }
}