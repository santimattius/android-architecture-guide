package com.santimattius.template.domain.usecases

import com.santimattius.template.domain.repositories.PicturesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPicturesTest {

    private lateinit var repository: PicturesRepository
    private lateinit var useCase: GetPictures

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetPictures(repository)

    }

    @Test
    fun `invoke get picture use case`() = runBlockingTest {

        coEvery { repository.getPictures() } returns emptyList()

        val result = useCase.invoke()

        assert(result.isEmpty())

        coVerify { repository.getPictures() }

    }
}