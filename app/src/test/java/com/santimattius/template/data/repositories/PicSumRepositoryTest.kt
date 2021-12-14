package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PicSumRepositoryTest {

    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: PicSumRepository

    @Before
    fun setUp() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        repository = PicSumRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `get picture on cached`() = runBlockingTest {

        coEvery { localDataSource.isEmpty() } returns false
        coEvery { localDataSource.getPictures() } returns emptyList()

        val pictures = repository.getPictures()

        assert(pictures.isEmpty())

        coVerify { localDataSource.isEmpty() }
        coVerify { localDataSource.getPictures() }
    }

    @Test
    fun `get picture updating cache`() = runBlockingTest {

        coEvery { localDataSource.isEmpty() } returns true
        coEvery { remoteDataSource.getPictures() } returns emptyList()
        coEvery { localDataSource.insertPictures(any()) } returns Unit
        coEvery { localDataSource.getPictures() } returns emptyList()

        val pictures = repository.getPictures()

        assert(pictures.isEmpty())

        coVerify { localDataSource.isEmpty() }
        coVerify { remoteDataSource.getPictures() }
        coVerify { localDataSource.insertPictures(any()) }
        coVerify { localDataSource.getPictures() }
    }
}