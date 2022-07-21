package com.voidx.photo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.voidx.github.core.lifecycle.DefaultViewModelProviderFactory
import com.voidx.github.core.lifecycle.ViewModelKey
import com.voidx.photo.data.api.FlickrAPI
import com.voidx.photo.data.repository.PhotoListRepository
import com.voidx.photo.data.repository.impl.PhotoListRepositoryImpl
import com.voidx.photo.data.repository.impl.remote.PhotoListRemoteRepositoryImpl
import com.voidx.photo.data.repository.impl.remote.mapper.PhotoListRepositoryMapper
import com.voidx.photo.ui.domain.GetPhotoListUseCase
import com.voidx.photo.ui.domain.PhotoDtoMapper
import com.voidx.photo.ui.presentation.PhotoListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(
    includes = [
        PhotoListDataModule::class,
        PhotoListDomainModule::class,
        PhotoListPresentationModule::class
    ]
)
internal interface PhotoListInternalModule

@Module
internal interface PhotoListDataModule {

    companion object {

        @[Provides]
        fun providesPhotoRepository(
            api: FlickrAPI,
            mapper: PhotoListRepositoryMapper
        ): PhotoListRepository {
            val remote = PhotoListRemoteRepositoryImpl(api, mapper)
            return PhotoListRepositoryImpl(remote)
        }

        @[Provides]
        fun providesFlickrAPI(retrofit: Retrofit): FlickrAPI {
            return retrofit.create(FlickrAPI::class.java)
        }

        @[Provides]
        fun providesResponseApiMapperPhotoList(gson: Gson): PhotoListRepositoryMapper {
            return PhotoListRepositoryMapper.Impl(gson)
        }
    }
}

@Module
internal interface PhotoListDomainModule {

    @[Binds]
    fun bindsPhotoDtoMapper(mapper: PhotoDtoMapper.Impl): PhotoDtoMapper

    @[Binds]
    fun bindsPhotoListUseCase(useCase: GetPhotoListUseCase.Impl): GetPhotoListUseCase
}

@Module
internal interface PhotoListPresentationModule {

    @[Binds IntoMap ViewModelKey(PhotoListViewModel::class)]
    fun bindsMoviesViewModel(viewModel: PhotoListViewModel): ViewModel

    @[Binds]
    fun bindsDefaultViewModelProviderFactory(
        viewModelProviderFactory: DefaultViewModelProviderFactory
    ): ViewModelProvider.Factory
}
