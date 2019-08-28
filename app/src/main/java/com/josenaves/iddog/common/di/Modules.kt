package com.josenaves.iddog.common.di

import com.josenaves.iddog.data.AuthRepository
import com.josenaves.iddog.data.remote.AuthRemoteDatasource
import com.josenaves.iddog.data.ConfigurationRepository
import com.josenaves.iddog.data.DogRepository
import com.josenaves.iddog.data.local.ConfigurationLocalDatasource
import com.josenaves.iddog.data.remote.DogRemoteDatasource
import com.josenaves.iddog.data.remote.api.DogApiClient
import com.josenaves.iddog.presentation.dog.DogsViewModel
import com.josenaves.iddog.presentation.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {

    // view models
    viewModel { LoginViewModel(get(), get()) }
    viewModel { DogsViewModel(get()) }

    // repositories
    single { ConfigurationRepository(get()) }
    single { AuthRepository(get()) }
    single { DogRepository(get()) }

    // datasources
    single { AuthRemoteDatasource(get()) }
    single { ConfigurationLocalDatasource(androidContext()) }
    single { DogRemoteDatasource(get()) }

    // remote api
    single { DogApiClient.create() }
}