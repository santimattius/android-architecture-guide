package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.datasources.implementation.database.PicSumDao
import com.santimattius.template.domain.entities.Picture
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
class RoomDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val picSumDao: PicSumDao = mockk()
    private val roomDataSource: RoomDataSource = RoomDataSource(
        picSumDao = picSumDao,
        dispatcher = coroutinesTestRule.testDispatcher
    )

    @Test
    fun `validate is empty check`() {
        // Given
        coEvery { picSumDao.count() } returns 0
        // When
        runTest {
            val isEmpty = roomDataSource.isEmpty()
            // Then
            assertThat(isEmpty, IsEqual(true))
        }

    }

    @Test
    fun `validate no is empty check`() {
        // Given
        coEvery { picSumDao.count() } returns 10
        // When
        runTest {
            val isEmpty = roomDataSource.isEmpty()
            // Then
            assertThat(isEmpty, IsEqual(false))
        }
    }

    @Test
    fun getPictures() {
        // Given
        coEvery { picSumDao.getAll() } returns emptyList()
        // When
        runTest {
            val pictures = roomDataSource.getPictures()
            // Then
            assertThat(pictures, IsEqual(emptyList()))
        }
    }

    @Test
    fun insertPictures() {
        // Given
        val pictures = emptyList<Picture>()
        coEvery { picSumDao.insertPictures(emptyList()) } returns Unit
        // When
        runTest { roomDataSource.insertPictures(pictures) }
        // Then
        coVerify { picSumDao.insertPictures(emptyList()) }
    }
}
