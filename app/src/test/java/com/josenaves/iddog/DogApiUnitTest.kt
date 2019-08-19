package com.josenaves.iddog

import com.josenaves.iddog.data.remote.api.DogApi
import com.josenaves.iddog.data.remote.api.DogApiClient
import com.josenaves.iddog.data.remote.api.dto.AuthRequest
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DogApiUnitTest {

    private lateinit var dogApi: DogApi

    @Before
    fun setup() {
        dogApi = DogApiClient.create()
    }

    @Test
    fun `dogApi is ok`() {
        assertNotNull(dogApi)
    }

    @Test
    fun `Should get a token from auth`() {
        runBlocking {
            val response = dogApi.auth(AuthRequest("josenaves@gmail.com"))
            println(response)
            println(response.body())

            assert(response.isSuccessful)
            assertNotNull(response.body())
            assertNotNull(response.body()?.user?.token)
        }
    }

    @Test
    fun `Should get dogs`() {
        runBlocking {
            val response = dogApi.getDogs()
            println(response)
            println(response.body())

            assert(response.isSuccessful)
            assertNotNull(response.body()?.category)
            assertNotNull(response.body()?.list)
            assert(response.body()?.list?.isNotEmpty() ?: true)
        }
    }
}
