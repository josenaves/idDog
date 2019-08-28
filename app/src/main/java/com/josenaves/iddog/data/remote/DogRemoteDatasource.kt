package com.josenaves.iddog.data.remote

import com.josenaves.iddog.data.DogDatasource
import com.josenaves.iddog.data.remote.api.DogApi
import com.josenaves.iddog.model.Dog
import java.io.IOException

class DogRemoteDatasource(private val api: DogApi) : DogDatasource {

    override suspend fun getAll(breed: String): List<Dog> {
        val response = api.getDogs(category = breed)
        if (response.isSuccessful) {
            val body = response.body()
            val breed = body?.category ?: breed
            val list = body?.list
            return list?.map { Dog(breed, it)} ?: emptyList()
        }

        throw IOException("Error getting dogs")
    }
}