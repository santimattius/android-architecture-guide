package com.santimattius.template.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santimattius.template.domain.usecases.GetPictures
import com.santimattius.template.ui.models.HomeState
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `check case when init view model`() = runBlockingTest {
        //Given
        val userCase = mockk<GetPictures>()
        coEvery { userCase() } returns emptyList()
        //When
        val viewModel = HomeViewModel(userCase)
        //Then
        assertThat(viewModel.state, IsEqual(HomeState(pictures = emptyList())))
    }


    @Test
    fun `check when init fail with exception`() = runBlockingTest {
        //Given
        val userCase = mockk<GetPictures>()
        coEvery { userCase() } throws Exception()
        //When
        val viewModel = HomeViewModel(userCase)
        assertThat(viewModel.state, IsEqual(HomeState(withError = true)))
    }

    @Test
    fun `check case with retry`() = runBlockingTest {
        //Given
        val userCase = mockk<GetPictures>()
        coEvery { userCase() } returns emptyList()
        //When
        val viewModel = HomeViewModel(userCase)
        viewModel.retry()
        //Then
        assertThat(viewModel.state, IsEqual(HomeState(pictures = emptyList())))
    }

}