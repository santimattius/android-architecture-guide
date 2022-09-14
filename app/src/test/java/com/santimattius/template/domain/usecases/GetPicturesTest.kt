package com.santimattius.template.domain.usecases

import com.santimattius.template.domain.repositories.PicturesRepository
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
class GetPicturesTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val repository: PicturesRepository = mockk()
    private val useCase: GetPictures = GetPictures(repository)

    @Test
    fun `invoke get picture use case`() {
        // Given
        coEvery { repository.getPictures() } returns emptyList()
        runTest {// When
            val result = useCase.invoke()
            // Then
            assertThat(result, IsEqual(emptyList()))
        }
        coVerify { repository.getPictures() }
    }
}
