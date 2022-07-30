package com.santimattius.template.di

import android.app.Application
import com.santimattius.template.core.data.RetrofitCreator
import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.datasources.implementation.RoomDataSource
import com.santimattius.template.data.datasources.implementation.ServiceDataSource
import com.santimattius.template.data.datasources.implementation.client.PicSumClient
import com.santimattius.template.data.datasources.implementation.client.PicSumService
import com.santimattius.template.data.datasources.implementation.database.PicSumDataBase
import com.santimattius.template.data.repositories.PicSumRepository
import com.santimattius.template.domain.repositories.PicturesRepository
import com.santimattius.template.domain.usecases.GetPictures
import com.santimattius.template.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class Dependencies {

    @Single
    fun provideDataBase(application: Application) = PicSumDataBase.create(application)

    @Factory
    fun provideService() =
        RetrofitCreator.service<PicSumService>("https://pictures.getsandbox.com:443")

    @Factory
    fun provideClient(service: PicSumService) = PicSumClient(service)

    @Factory
    fun provideLocalDataSource(db: PicSumDataBase): LocalDataSource =
        RoomDataSource(db.picSumDao(), Dispatchers.IO)

    @Factory
    fun provideRemoteDataSource(client: PicSumClient): RemoteDataSource = ServiceDataSource(client)

    @Factory
    fun provideGetPictures(repository: PicturesRepository) = GetPictures(repository)

    @KoinViewModel
    fun provideViewModel(getPictures: GetPictures) = HomeViewModel(getPictures)

    @Factory
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): PicturesRepository = PicSumRepository(localDataSource, remoteDataSource)
}
