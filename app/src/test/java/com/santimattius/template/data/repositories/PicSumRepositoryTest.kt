package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PicSumRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val localDataSource: LocalDataSource = mockk()
    private val remoteDataSource: RemoteDataSource = mockk()
    private val repository = PicSumRepository(localDataSource, remoteDataSource)

    @Test
    fun `get picture on cached`() {

        coEvery { localDataSource.isEmpty() } returns false
        coEvery { localDataSource.getPictures() } returns emptyList()

        runTest {
            val pictures = repository.getPictures()

            assert(pictures.isEmpty())
        }
        coVerify { localDataSource.isEmpty() }
        coVerify { localDataSource.getPictures() }
    }

    @Test
    fun `get picture updating cache`() {

        coEvery { localDataSource.isEmpty() } returns true
        coEvery { remoteDataSource.getPictures() } returns emptyList()
        coEvery { localDataSource.insertPictures(any()) } returns Unit
        coEvery { localDataSource.getPictures() } returns emptyList()

        runTest {
            val pictures = repository.getPictures()

            assert(pictures.isEmpty())
        }

        coVerify { localDataSource.isEmpty() }
        coVerify { remoteDataSource.getPictures() }
        coVerify { localDataSource.insertPictures(any()) }
        coVerify { localDataSource.getPictures() }
    }
}
