package com.josenaves.iddog.data

import com.josenaves.iddog.data.remote.DogRemoteDatasource
import com.josenaves.iddog.model.Dog

class DogRepository(private val remote: DogRemoteDatasource) : DogDatasource {

    override suspend fun getAll(breed: String): List<Dog> {
        return remote.getAll(breed)
    }
}