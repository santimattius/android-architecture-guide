package com.santimattius.template.di

import com.santimattius.template.core.data.service
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
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * presentation layer definition module
 */
private val presentationModule = module {
    viewModel { HomeViewModel(getPictures = get<GetPictures>()) }
}


/**
 * domain layer definition module
 */
private val domainModule = module {
    factory { GetPictures(repository = get<PicturesRepository>()) }
}

/**
 * data layer definition module
 */
private val dataModule = module {

    single<PicSumService> { service("https://pictures.getsandbox.com:443") }

    single<PicSumClient> { PicSumClient(get<PicSumService>()) }

    factory<RemoteDataSource> { ServiceDataSource(client = get<PicSumClient>()) }

    factory<LocalDataSource> {
        RoomDataSource(
            picSumDao = PicSumDataBase.create(
                androidApplication()
            ).picSumDao(),
            dispatcher = Dispatchers.IO
        )
    }

    factory<PicturesRepository> {
        PicSumRepository(
            localDataSource = get<LocalDataSource>(),
            remoteDataSource = get<RemoteDataSource>()
        )
    }

}


internal val modules = listOf(presentationModule, domainModule, dataModule)
