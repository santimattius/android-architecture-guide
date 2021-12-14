package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.core.data.Result
import com.santimattius.template.data.datasources.implementation.client.PicSumClient
import com.santimattius.template.data.datasources.implementation.client.ServiceError
import com.santimattius.template.data.models.NetworkPicture
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class ServiceDataSourceTest {

    private lateinit var serviceDataSource: ServiceDataSource

    private lateinit var picSumClient: PicSumClient

    @Before
    fun setUp() {
        picSumClient = mockk()
        serviceDataSource = ServiceDataSource(picSumClient)
    }

    @Test
    fun `get picture on client result is success`() = runBlockingTest {

        val pictures = (1..10).map { NetworkPicture(id = it.toString()) }

        coEvery { picSumClient.fetchList() } returns Result.success(pictures)

        val result = serviceDataSource.getPictures()

        Assert.assertEquals(pictures, result)

        coVerify { picSumClient.fetchList() }
    }

    @Test
    fun `get picture on client result is fail`() = runBlockingTest {

        coEvery { picSumClient.fetchList() } returns Result.failure(ServiceError("test case on fail"))

        val result = serviceDataSource.getPictures()

        assert(result.isEmpty())

        coVerify { picSumClient.fetchList() }
    }
}