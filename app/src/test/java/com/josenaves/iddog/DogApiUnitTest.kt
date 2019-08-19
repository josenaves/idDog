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
            assertNotNull(response.body())
        }
    }

//    @Test
//    fun `User should get all sections from api`() {
//        runBlocking {
//
//            val response = dogApi.getSectionsAsync().await()
//            assert(response.isSuccessful)
//
//            val apiResponse = response.body()
//            assertNotNull(apiResponse)
//
//            val linkStructure = apiResponse?.links
//            assertNotNull(linkStructure)
//
//            val sections = linkStructure?.sections
//            assertNotNull(sections)
//            assert(sections!!.isNotEmpty())
//        }
//    }
//
//    @Test
//    fun `User should get series section detail page`() {
//        runBlocking {
//
//            val response = dogApi.getSectionDetailsAsync("serier").await()
//            assert(response.isSuccessful)
//
//            val sectionPage = response.body()
//            assertNotNull(sectionPage)
//
//            assertEquals("page", sectionPage?.type)
//            assertEquals("section", sectionPage?.pageType)
//            assertEquals("35bb8a90-d40e-11e2-8b8b-0800200c9a66", sectionPage?.sectionId)
//            assertEquals("TV-serier online - Streama serier på nätet eller ladda ned", sectionPage?.title)
//
//            assertEquals(
//                "Viaplay erbjuder TV-serier på nätet för hela familjen. Streama serier online i hög kvalitet eller ladda ned till tablet eller mobil!",
//                sectionPage?.description
//            )
//
//
//        }
//    }

}
