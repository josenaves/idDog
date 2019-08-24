package com.josenaves.iddog.common.di

import com.josenaves.iddog.data.AuthRepository
import com.josenaves.iddog.data.remote.AuthRemoteDatasource
import com.josenaves.iddog.data.ConfigurationRepository
import com.josenaves.iddog.data.local.ConfigurationLocalDatasource
import com.josenaves.iddog.data.remote.api.DogApiClient
import com.josenaves.iddog.presentation.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override = true) {

    // view models
    viewModel { LoginViewModel(get(), get()) }

    // repositories
    single { ConfigurationRepository(get()) }
    single { AuthRepository(get()) }

    // datasources
    single { AuthRemoteDatasource(get()) }
    single { ConfigurationLocalDatasource(androidContext()) }

    // remote api
    single { DogApiClient.create() }
}