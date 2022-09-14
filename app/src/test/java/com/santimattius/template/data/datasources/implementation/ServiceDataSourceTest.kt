package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.core.data.Result
import com.santimattius.template.data.datasources.implementation.client.PicSumClient
import com.santimattius.template.data.datasources.implementation.client.ServiceError
import com.santimattius.template.objectmothers.NetworkPictureMother
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ServiceDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val picSumClient: PicSumClient = mockk()
    private val serviceDataSource: ServiceDataSource = ServiceDataSource(picSumClient)

    @Test
    fun `get picture on client result is success`() {
        // Given
        val pictures = NetworkPictureMother.pictures()
        coEvery { picSumClient.fetchList() } returns Result.success(pictures)
        // When
        runTest {
            val result = serviceDataSource.getPictures()
            // Then
            assertThat(result, IsEqual(pictures))
        }
        coVerify { picSumClient.fetchList() }
    }

    @Test
    fun `get picture on client result is fail`() {
        // Given
        val failure = Result.failure(ServiceError("test case on fail"))
        coEvery { picSumClient.fetchList() } returns failure
        // When
        runTest {
            val result = serviceDataSource.getPictures()
            // Then
            assertThat(result, IsEqual(emptyList()))
        }
        coVerify { picSumClient.fetchList() }
    }
}
