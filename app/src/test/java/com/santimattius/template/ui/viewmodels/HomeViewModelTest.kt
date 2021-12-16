package com.santimattius.template.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santimattius.template.domain.usecases.GetPictures
import com.santimattius.template.ui.models.HomeState
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
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

        val userCase = mockk<GetPictures>()

        coEvery { userCase() } returns emptyList()

        val viewModel = HomeViewModel(userCase)

        Assert.assertEquals(viewModel.state, HomeState(pictures = emptyList()))
    }


    @Test
    fun `check when init fail with exception`() = runBlockingTest {

        val userCase = mockk<GetPictures>()

        coEvery { userCase() } throws Exception()

        val viewModel = HomeViewModel(userCase)

        Assert.assertEquals(viewModel.state, HomeState(withError = true))
    }

    @Test
    fun `check case with retry`() = runBlockingTest {

        val userCase = mockk<GetPictures>()

        coEvery { userCase() } returns emptyList()

        val viewModel = HomeViewModel(userCase)

        viewModel.retry()

        Assert.assertEquals(viewModel.state, HomeState(pictures = emptyList()))
    }

}