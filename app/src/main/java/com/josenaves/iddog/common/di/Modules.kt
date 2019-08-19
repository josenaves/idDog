package com.josenaves.iddog.common.di

import com.josenaves.iddog.data.remote.api.DogApiClient
import org.koin.dsl.module.module

val applicationModule = module(override = true) {
    // remote api
    single { DogApiClient.create() }
}