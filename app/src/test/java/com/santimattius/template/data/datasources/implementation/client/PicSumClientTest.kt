package com.santimattius.template.data.datasources.implementation.client

import com.santimattius.template.core.data.Failure
import com.santimattius.template.core.data.Success
import com.santimattius.template.data.models.NetworkPicture
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PicSumClientTest {

    private lateinit var service: PicSumService
    private lateinit var client: PicSumClient

    @Before
    fun setUp() {
        service = mockk()
        client = PicSumClient(service)
    }

    @Test
    fun `fetch service with response case`() = runBlockingTest {
        coEvery { service.fetchList() } returns emptyList()
        val response = client.fetchList()
        Assert.assertEquals(
            emptyList<NetworkPicture>(),
            (response as Success<List<NetworkPicture>>).out
        )
    }

    @Test
    fun `fetch service with exception`() = runBlockingTest {
        val message = "fake_message"
        coEvery { service.fetchList() } throws Throwable(message)
        val response = client.fetchList()

        Assert.assertEquals(
            ServiceError(message),
            (response as Failure).exception
        )
    }
}