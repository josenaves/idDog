package com.josenaves.iddog.data

import com.josenaves.iddog.data.remote.api.HUSKY
import com.josenaves.iddog.model.Dog

interface DogDatasource {
    suspend fun getAll(breed: String = HUSKY) : List<Dog> = emptyList()
}