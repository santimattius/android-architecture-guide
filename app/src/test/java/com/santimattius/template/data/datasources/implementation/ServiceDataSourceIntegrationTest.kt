package com.santimattius.template.data.datasources.implementation

import com.google.gson.Gson
import com.santimattius.template.core.data.RetrofitCreator
import com.santimattius.template.data.datasources.implementation.client.PicSumClient
import com.santimattius.template.objectmothers.NetworkPictureMother
import com.santimattius.template.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ServiceDataSourceIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val mockWebServer = MockWebServer()
    private lateinit var picSumClient: PicSumClient
    private lateinit var serviceDataSource: ServiceDataSource

    @Before
    fun setup() {
        mockWebServer.start(8080)
        val baseUrl = mockWebServer.url("/").toUri().toString()
        picSumClient = PicSumClient(RetrofitCreator.service(baseUrl))
        serviceDataSource = ServiceDataSource(picSumClient)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get picture on client result is success`() = runBlocking {
        // Given
        val pictures = NetworkPictureMother.pictures()
        val response = MockResponse()
            .setBody(Gson().toJson(pictures))
            .setResponseCode(200)

        mockWebServer.enqueue(response)
        // When
        val result = serviceDataSource.getPictures()
        // Then
        assertThat(result, IsEqual(pictures))
    }

    @Test
    fun `get picture on client result is fail`() = runBlocking {
        // Given
        val response = MockResponse()
            .setResponseCode(500)

        mockWebServer.enqueue(response)
        // When
        val result = serviceDataSource.getPictures()
        // Then
        assertThat(result, IsEqual(emptyList()))
    }
}
