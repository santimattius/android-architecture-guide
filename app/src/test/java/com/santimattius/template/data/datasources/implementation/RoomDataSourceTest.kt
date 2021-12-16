package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.datasources.implementation.database.PicSumDao
import com.santimattius.template.domain.entities.Picture
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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
    fun `validate is empty check`() = runBlockingTest {
        //Given
        every { picSumDao.count() } returns 0
        // When
        val isEmpty = roomDataSource.isEmpty()
        // Then
        assertThat(isEmpty, IsEqual(true))
    }

    @Test
    fun `validate no is empty check`() = runBlockingTest {
        //Given
        every { picSumDao.count() } returns 10
        // When
        val isEmpty = roomDataSource.isEmpty()
        // Then
        assertThat(isEmpty, IsEqual(false))
    }

    @Test
    fun getPictures() = runBlockingTest {
        //Given
        every { picSumDao.getAll() } returns emptyList()
        //When
        val pictures = roomDataSource.getPictures()
        //Then
        assertThat(pictures, IsEqual(emptyList()))
    }

    @Test
    fun insertPictures() = runBlockingTest {
        //Given
        val pictures = emptyList<Picture>()
        every { picSumDao.insertPictures(emptyList()) } returns Unit
        //When
        roomDataSource.insertPictures(pictures)
        //Then
        verify { picSumDao.insertPictures(emptyList()) }
    }
}